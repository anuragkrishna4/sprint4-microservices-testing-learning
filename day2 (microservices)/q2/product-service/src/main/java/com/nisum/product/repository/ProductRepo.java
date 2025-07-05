package com.nisum.product.repository;


import com.nisum.product.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepo {
    private final Map<Long, Product> db = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public Product save(Product p) {
        long id = seq.getAndIncrement();
        p.setId(id);
        db.put(id, p);
        return p;
    }

    public List<Product> findAll() {
        return new ArrayList<>(db.values());
    }
}
