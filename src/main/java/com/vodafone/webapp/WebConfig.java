package com.vodafone.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vodafone.webapp.services.CustomerService;

import lombok.AllArgsConstructor;

// Try using security modules on spring for the password, encrypt the password.

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebConfig {

    private final CustomerService customerService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.userDetailsService(customerService)
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers("/h2-console/**"))
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/register", "/login", "/*.css",
                                        "/h2-console/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())

                .formLogin(form -> form.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=invalid credentials")
                        .permitAll())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
                .headers(headers -> headers.frameOptions(options -> options.disable()));


        return httpSecurity.build();
    }

}
