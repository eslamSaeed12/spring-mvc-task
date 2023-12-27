package com.vodafone.webapp.services;

import com.vodafone.webapp.exceptions.UniquenessViolationException;
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

    public Customer register(Customer registerDto) throws UniquenessViolationException {

        boolean isCustomerExist = isUsernameOrEmailExists(registerDto.getUsername(), registerDto.getEmail());

        if (isCustomerExist) {
            throw new UniquenessViolationException();
        }

        registerDto.setPassword(this.bcrypt.encode(registerDto.getPassword()));

        return this.customerRepository.save(registerDto);
    }

    private boolean isUsernameOrEmailExists(String username, String email) {
        Customer customer = this.customerRepository.findByUsernameOrEmail(username, email);
        return customer != null;
    }

}
