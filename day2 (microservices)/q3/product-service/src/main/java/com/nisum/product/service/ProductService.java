package com.nisum.product.service;

import com.nisum.product.model.Product;
import com.nisum.product.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repo;

    public Product create(Product p) {
        return repo.save(p);
    }

    public List<Product> list() {
        return repo.findAll();
    }
}
