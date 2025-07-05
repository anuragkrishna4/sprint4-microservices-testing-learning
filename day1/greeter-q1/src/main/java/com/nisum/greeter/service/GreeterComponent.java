package com.nisum.greeter.service;

import org.springframework.stereotype.Component;

@Component
public class GreeterComponent implements Greeter {
    @Override
    public String greet(String name) {
        return "Hello, " + name + " (from component)";
    }
}
