package com.nisum.product.controller;

import com.nisum.product.model.Product;
import com.nisum.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService svc;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product body) {
        Product created = svc.create(body);
        return ResponseEntity.created(URI.create("/products/" + created.getId())).body(created);
    }

    @GetMapping
    public List<Product> list() {
        return svc.list();
    }
}
