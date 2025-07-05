package com.nisum.user.repository;

import com.nisum.user.model.User;

public interface UserRepository {
    User findByEmail(String email);
}
