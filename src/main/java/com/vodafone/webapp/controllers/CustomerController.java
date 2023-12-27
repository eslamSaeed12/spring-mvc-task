package com.vodafone.webapp.controllers;

import com.vodafone.webapp.entites.Customer;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vodafone.webapp.dtos.LoginForm;
import com.vodafone.webapp.dtos.RegisterForm;
import com.vodafone.webapp.services.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/register")
    public String GetRegisterPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String Register(@Valid @ModelAttribute("registerForm") Customer registerDto,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            this.customerService.register(registerDto);
        } catch (Exception exception) {
            bindingResult.addError(new FieldError("registerForm", "username", exception.getMessage()));
            return "register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String GetLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @GetMapping("/")
    public String GetHomePage(Model model, Authentication authentication) {
        return "index";
    }

}
