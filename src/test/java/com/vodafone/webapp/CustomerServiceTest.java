package com.vodafone.webapp;

import com.vodafone.webapp.entites.Customer;
import com.vodafone.webapp.exceptions.UniquenessViolationException;
import com.vodafone.webapp.repositores.CustomerRepository;
import com.vodafone.webapp.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService_;

    @Test
    public void testRegistration() throws Exception {
        Customer registerForm = new Customer();

        registerForm.setFirstName("islam");
        registerForm.setLastName("saeed");
        registerForm.setEmail("islam@gmail.com");
        registerForm.setPassword("password");
        registerForm.setPhoneNumber("01006885750");
        registerForm.setUsername("islamSaeed15");

        Customer customer = this.customerService_.register(registerForm);

        assertEquals(customer.getUsername(), registerForm.getUsername());
    }


    @Test
    public void testLoadUserByUsername() throws UsernameNotFoundException, UniquenessViolationException {

        Customer registerForm = new Customer();

        registerForm.setFirstName("islam");
        registerForm.setLastName("saeed");
        registerForm.setEmail("islam16@gmail.com");
        registerForm.setPassword("password");
        registerForm.setPhoneNumber("01006885750");
        registerForm.setUsername("islamSaeed16");

        Customer customer = this.customerService_.register(registerForm);

        UserDetails toFindCustomer = this.customerService_.loadUserByUsername(registerForm.getUsername());

        // when user is actually exist
        assertEquals(toFindCustomer.getUsername(), registerForm.getUsername());

        // when user isn't existed
        assertThrows(UsernameNotFoundException.class, () -> this.customerService_.loadUserByUsername("unknown value"));
    }

    @Test
    public void testUsernameAndEmailDuplication() throws UniquenessViolationException {
        Customer registerForm = new Customer();

        registerForm.setFirstName("islam");
        registerForm.setLastName("saeed");
        registerForm.setEmail("islam21@gmail.com");
        registerForm.setPassword("password1");
        registerForm.setPhoneNumber("01006885750");
        registerForm.setUsername("islamSaeed17");

        // first register , no duplication
        this.customerService_.register(registerForm);

        // throws due to duplication
        assertThrows(UniquenessViolationException.class, () -> this.customerService_.register(registerForm));
    }

}
