package com.nisum.user.service;

import com.nisum.user.dto.CreateUserRequest;
import com.nisum.user.model.User;
import com.nisum.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    public User create(CreateUserRequest req) {
        User user = new User(null, req.name(), req.email(), req.phone());
        return repo.save(user);
    }

    public User get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<User> list() {
        return repo.findAll();
    }
}
