package com.vodafone.webapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {

    @NotBlank(message = "username cannot be empty")
    @Size(min = 8, max = 24, message = "username length must be between 8 to 24 characters")
    public String username;

    @NotBlank(message = "username cannot be empty")
    @Size(min = 8, max = 24, message = "password length must be between 8 to 12 characters")
    public String password;
}
