package com.nisum.user.service;

import com.nisum.user.email.EmailSender;
import com.nisum.user.model.User;
import com.nisum.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailSender emailSender;

    public void processUser(@NonNull String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            handleMissingUser(email);
            throw new RuntimeException("User not found");
        }

        emailSender.send(user.getEmail(), "Welcome", "Hello " + user.getName());
    }

    public void handleMissingUser(String email) {
        log.warn("Fallback for missing user: {}", email);
    }
}
