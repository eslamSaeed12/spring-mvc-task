package com.vodafone.webapp.dtos;

import com.vodafone.webapp.reflectors.PhoneNumber;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterForm {

    @NotBlank(message = "username cannot be empty")
    @Size(min = 8, max = 24, message = "username length must be between 8 to 24 characters")
    public String username;

    @NotBlank(message = "username cannot be empty")
    @Size(min = 8, max = 24, message = "password length must be between 8 to 12 characters")
    public String password;

    @NotBlank(message = "first name cannot be empty")
    @Size(min = 4, max = 16, message = "firstname length must be between 8 to 16 characters")
    public String firstName;

    @NotBlank(message = "last name cannot be empty")
    @Size(min = 4, max = 16, message = "last name length must be between 8 to 16 characters")
    public String lastName;

    @NotBlank(message = "phone number cannot be empty")
    @Size(min = 4, max = 16, message = "phone number length must be between 8 to 16 characters")
    @PhoneNumber
    public String phoneNumber;

    @NotBlank(message = "email cannot be empty")
    @Size(min = 4, max = 32, message = "email length must be between 4 to 32 characters")
    @Email
    public String email;
}
