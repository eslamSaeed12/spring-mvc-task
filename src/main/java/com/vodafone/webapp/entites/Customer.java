package com.vodafone.webapp.entites;

import java.util.Collection;
import java.util.Collections;

import com.vodafone.webapp.reflectors.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "username cannot be empty")
    @Size(min = 8, max = 24, message = "username length must be between 8 to 24 characters")
    private String username;

    @Column(unique = true)
    @NotBlank(message = "email cannot be empty")
    @Size(min = 4, max = 32, message = "email length must be between 4 to 32 characters")
    @Email
    private String email;

    @NotBlank(message = "username cannot be empty")
    @Size(min = 4)
    private String password;


    @NotBlank(message = "first name cannot be empty")
    @Size(min = 4, max = 16, message = "firstname length must be between 8 to 16 characters")
    private String firstName;

    @NotBlank(message = "last name cannot be empty")
    @Size(min = 4, max = 16, message = "last name length must be between 8 to 16 characters")
    private String lastName;

    @NotBlank(message = "phone number cannot be empty")
    @Size(min = 4, max = 16, message = "phone number length must be between 8 to 16 characters")
    @PhoneNumber
    private String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
