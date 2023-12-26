package com.vodafone.webapp.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vodafone.webapp.entites.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByUsername(String username);

    @Query("SELECT c FROM Customer c WHERE username=?1 AND email=?2")
    public Customer findByUsernameOrEmail(String username, String Email);
}
