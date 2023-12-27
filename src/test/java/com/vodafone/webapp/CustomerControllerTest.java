package com.vodafone.webapp;


import com.vodafone.webapp.entites.Customer;
import com.vodafone.webapp.exceptions.UniquenessViolationException;
import com.vodafone.webapp.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    // test page is redirected to /login when unauthorized user
    @Test
    public void testUnauthorizedAccessRedirectsToLogin() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }

    // test register is failed due to validation
    @Test
    public void testRegisteredFailureDueValidation() throws Exception {
        this.mockMvc.perform(post("/register")
                        .param("username", "") // invalid username (empty)
                        .param("password", "123") // invalid password (less than required length)
                        .param("email", "invalid-email") // invalid email
                        .param("phoneNumber", "") // blank phone number
                        .param("firstName", "") // blank first name
                        .param("lastName", "") // blank last name
                )
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeHasFieldErrors("registerForm", "username", "password", "email", "phoneNumber", "firstName", "lastName"));
    }

    @Test
    public void testRegisteredSuccess() throws Exception {
        this.mockMvc.perform(post("/register")
                        .param("username", "islamSaeed12")
                        .param("password", "islamSaeed")
                        .param("email", "islam12@gmail.com")
                        .param("phoneNumber", "01006885750")
                        .param("firstName", "islam")
                        .param("lastName", "saeed")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }


    private Customer createTestCustomer() throws UniquenessViolationException {
        Customer registerForm = new Customer();

        registerForm.setFirstName("islam");
        registerForm.setLastName("saeed");
        registerForm.setEmail("islam@gmail.com");
        registerForm.setPassword("islamSaeed");
        registerForm.setPhoneNumber("01006885750");
        registerForm.setUsername("islamSaeed13");

        return this.customerService.register(registerForm);
    }


    // testing login with valid credentials
    @Test
    public void testValidLogin() throws Exception {

        Customer customer = this.createTestCustomer();

        this.mockMvc.perform(post("/login")
                        .param("username", "islamSaeed13")
                        .param("password", "islamSaeed")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    // testing login with invalid credentials
    @Test
    public void testInValidLogin() throws Exception {
        this.mockMvc.perform(post("/login")
                        .param("username", "unknownUser")
                        .param("password", "invalid password")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=invalid credentials"));
    }


    // testing login with invalid credentials
    @Test
    public void testLogoutAfterLogin() throws Exception {
        this.mockMvc.perform(post("/login")
                        .param("username", "islamSaeed13")
                        .param("password", "islamSaeed")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        this.mockMvc.perform(post("/logout")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login?logout"));
        this.mockMvc.perform(get("/")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }


}
