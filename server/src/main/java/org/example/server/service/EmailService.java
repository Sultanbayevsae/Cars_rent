package org.example.server.service;

public interface EmailService {
    void sendVerificationEmail(String to, String name, String confirmUrl, String ignoreUrl);
}
