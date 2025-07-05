package com.nisum.user.service;

import com.nisum.user.dto.CreateUserRequest;
import com.nisum.user.dto.Product;
import com.nisum.user.model.Address;
import com.nisum.user.model.User;
import com.nisum.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;
    private final RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String productServiceUrl;

    public User create(CreateUserRequest req) {
        List<Address> addr = req.addresses().stream()
                .map(a -> new Address(a.street(), a.city(), a.state(), a.zipCode()))
                .toList();
        User u = new User(null, req.name(), req.email(), addr);
        return repo.save(u);
    }

    public User get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Product> getProductsForUser(Long userId) {
        String url = productServiceUrl;
        Product[] products = restTemplate.getForObject(url, Product[].class);
        return List.of(products);
    }

    public List<User> list() {
        return repo.findAll();
    }
}
