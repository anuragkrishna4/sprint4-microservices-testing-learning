package com.nisum.product.service;

import com.nisum.product.dto.CreateProductRequest;
import com.nisum.product.model.Product;
import com.nisum.product.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repo;

    public Product create(CreateProductRequest req) {
        Product p = new Product(null, req.name(), req.price());
        return repo.save(p);
    }

    public List<Product> list() {
        return repo.findAll();
    }

    public Product get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
