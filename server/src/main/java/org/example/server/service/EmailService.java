package org.example.server.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.server.entity.User;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendVerificationEmail(User user, String verificationToken) {
        String verificationUrl = "http://localhost:8080/api/v1/auth/verify-email?token=" + verificationToken;

        String htmlContent = createVerificationEmailHtml(user.getEmail(), verificationUrl);
        sendEmail(user.getEmail(), "Activate Your Car Rental Account", htmlContent);
    }

    private void sendEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("✅ Email sent to: {}", to);

        } catch (MessagingException e) {
            log.error("❌ Failed to send email to: {}", to, e);
        }
    }

    private String createVerificationEmailHtml(String userEmail, String verificationUrl) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; background: #f5f5f5; padding: 20px; }
                    .container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; }
                    .button { background: #007bff; color: white; padding: 12px 24px; text-decoration: none; border-radius: 5px; display: inline-block; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2> Welcome to MORENT!</h2>
                    <p>Hello <strong>%s</strong>,</p>
                    <p>Click the button below to activate your account:</p>
                    <a href="%s" class="button">Activate Account</a>
                    <p><small>Or ignore this email if you didn't create an account.</small></p>
                </div>
            </body>
            </html>
            """.formatted(userEmail, verificationUrl);
    }
}