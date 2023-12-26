package com.vodafone.webapp.services;

import org.springframework.stereotype.Service;
import com.vodafone.webapp.repositores.CustomerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

}
