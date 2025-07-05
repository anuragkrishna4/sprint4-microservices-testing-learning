package com.nisum.user.email;

public interface EmailSender {
    void send(String to, String subject, String body);
}
