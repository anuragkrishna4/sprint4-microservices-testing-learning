package com.nisum.greeter.config;

import com.nisum.greeter.service.Greeter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GreeterConfig {

    @Bean
    @Primary
    public Greeter greeterBean() {
        return name -> "Hello, " + name + " (from @Configuration)";
    }
}
