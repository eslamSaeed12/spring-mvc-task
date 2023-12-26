package com.vodafone.webapp;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@org.springframework.context.annotation.Configuration
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class Configuration {

}
