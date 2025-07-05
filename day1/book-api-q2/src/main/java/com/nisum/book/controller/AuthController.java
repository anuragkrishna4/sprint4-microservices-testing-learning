package com.nisum.book.controller;

import com.nisum.book.security.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/token")
    public String generateToken() {
        return JwtUtil.generate("admin");
    }
}
