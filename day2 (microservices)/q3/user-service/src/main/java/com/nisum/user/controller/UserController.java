package com.nisum.user.controller;

import com.nisum.user.dto.CreateUserRequest;
import com.nisum.user.model.User;
import com.nisum.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody CreateUserRequest body) {
        User created = userService.create(body);
        return ResponseEntity.created(URI.create("/user/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping
    public List<User> getUser(){
        return userService.list();
    }
}
