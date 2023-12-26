package com.vodafone.webapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.vodafone.webapp.WebConfig;
import com.vodafone.webapp.dtos.RegisterForm;
import com.vodafone.webapp.entites.Customer;
import com.vodafone.webapp.repositores.CustomerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService {

    final private CustomerRepository customerRepository;

    final private PasswordEncoder bcrypt = WebConfig.passwordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByUsername(username);

        if (customer == null) {
            throw new UsernameNotFoundException("invalid credentials or maybe user not exist");
        }

        return customer;
    }

    public void register(RegisterForm registerDto) throws Exception {

        boolean isCustomerExist = isUsernameOrEmailExists(registerDto.getUsername(), registerDto.getEmail());

        if (isCustomerExist) {
            throw new Exception("User Already Registered Before");
        }

        Customer customer = new Customer();

        customer.setUsername(registerDto.username);
        customer.setEmail(registerDto.email);
        customer.setPassword(this.bcrypt.encode(registerDto.password));
        customer.setFirstName(registerDto.firstName);
        customer.setLastName(registerDto.lastName);
        customer.setPhoneNumber(registerDto.phoneNumber);

        this.customerRepository.save(customer);
    }

    private boolean isUsernameOrEmailExists(String username, String email) {
        Customer customer = this.customerRepository.findByUsernameOrEmail(username, email);
        return customer != null;
    }

}
