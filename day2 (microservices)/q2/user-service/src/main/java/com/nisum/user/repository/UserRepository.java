package com.nisum.user.repository;

import com.nisum.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private final Map<Long, User> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public User save(User user) {
        long id = seq.getAndIncrement();
        user.setId(id);
        store.put(id, user);
        return user;
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }
}
