package com.nisum.product.controller;

import com.nisum.product.dto.CreateProductRequest;
import com.nisum.product.model.Product;
import com.nisum.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService svc;

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody CreateProductRequest body) {
        Product created = svc.create(body);
        URI location = URI.create("/products/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public List<Product> list() {
        return svc.list();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return svc.get(id);
    }
}
