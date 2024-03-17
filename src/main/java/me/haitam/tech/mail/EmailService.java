package me.haitam.tech.mail;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage message);
    SimpleMailMessage setEmail(String email, String name);
}

