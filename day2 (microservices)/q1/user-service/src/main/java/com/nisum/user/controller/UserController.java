package com.nisum.user.controller;

import com.nisum.user.dto.CreateUserRequest;
import com.nisum.user.dto.Product;
import com.nisum.user.model.User;
import com.nisum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService svc;

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody CreateUserRequest body) {
        User created = svc.create(body);
        URI location = URI.create("/user/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return svc.get(id);
    }

    @GetMapping
    public List<User> list() {
        return svc.list();
    }

    @GetMapping("/{id}/products")
    public List<Product> getUserProducts(@PathVariable Long id) {
        return svc.getProductsForUser(id);
    }

}
