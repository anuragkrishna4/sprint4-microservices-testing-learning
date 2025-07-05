package com.nisum.greeter.autoconfig;

import com.nisum.greeter.service.Greeter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Will back‑off automatically when *any* Greeter bean is already defined
 * (via component scan or explicit Java config).
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(Greeter.class)
public class GreeterAutoConfiguration {

    @Bean
    public Greeter autoGreeter() {
        return name -> "Hello, " + name + " (from auto‑config)";
    }
}
