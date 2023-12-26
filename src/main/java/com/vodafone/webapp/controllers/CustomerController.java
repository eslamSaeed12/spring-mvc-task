package com.vodafone.webapp.controllers;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vodafone.webapp.dtos.LoginDto;
import com.vodafone.webapp.dtos.RegisterDto;
import com.vodafone.webapp.services.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/register")
    public String GetReigster(Model model) {
        model.addAttribute("registerForm", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String PostRegister(@Valid @ModelAttribute("registerForm") RegisterDto registerDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String GetLogin(Model model) {
        model.addAttribute("loginForm", new LoginDto());
        return "login";
    }

    @PostMapping(path = "/login")
    public String PostLogin(@Valid @ModelAttribute("loginForm") LoginDto loginForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String GetLogout() {
        return "login";
    }

    @GetMapping("/")
    public String GetHomePage() {
        return "home";
    }

}
