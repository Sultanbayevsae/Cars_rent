package org.example.server.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.server.entity.Invoice;
import org.example.server.entity.User;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    /** Verification email */
    public void sendVerificationEmail(User user, String verificationToken) {
        String verificationUrl = "http://localhost:8080/api/v1/auth/verify-email?token=" + verificationToken;
        String ignoreVerificationUrl = "http://localhost:8080/api/v1/auth/ignore-verification?token=" + verificationToken;

        String htmlContent = createVerificationEmailHtml(user.getEmail(), verificationUrl, ignoreVerificationUrl);
        sendEmail(user.getEmail(), "Activate Your Car Rental Account", htmlContent, null, null);
    }

    /** Invoice email */
    public void sendInvoiceEmail(Invoice invoice) {
        String to = invoice.getUser().getEmail();
        String subject = "Your Invoice #" + invoice.getInvoiceNumber();
        String htmlContent = createInvoiceHtml(invoice);
        byte[] pdfBytes = generateInvoicePdf(invoice);

        sendEmail(to, subject, htmlContent, "Invoice-" + invoice.getInvoiceNumber() + ".pdf", pdfBytes);
    }

    /** Common send method */
    private void sendEmail(String to, String subject, String htmlContent, String attachmentName, byte[] attachmentBytes) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, attachmentBytes != null, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            if (attachmentBytes != null && attachmentName != null) {
                helper.addAttachment(attachmentName, new ByteArrayResource(attachmentBytes));
            }

            mailSender.send(message);
            log.info("✅ Email sent to: {}", to);

        } catch (MessagingException e) {
            log.error("❌ Failed to send email to: {}", to, e);
        }
    }

    private String createVerificationEmailHtml(String userEmail, String verificationUrl, String ignoreVerificationUrl) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; background: #f5f5f5; padding: 20px; }
                    .container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; }
                    .button { background: #007bff; color: white; padding: 12px 24px; text-decoration: none; border-radius: 5px; display: inline-block; }
                    .ignore-button { background: #d62c20; color: white; padding: 12px 24px; text-decoration: none; border-radius: 5px; display: inline-block; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2> Welcome to MORENT!</h2>
                    <p>Hello <strong>%s</strong>,</p>
                    <p>Click the button below to activate your account:</p>
                    <a href="%s" class="button">Activate Account</a>
                    <p><small>Or ignore this email if you didn't create an account.</small></p>
                    <a href="%s" class="ignore-button">Ignore Activation</a>
                </div>
            </body>
            </html>
            """.formatted(userEmail, verificationUrl, ignoreVerificationUrl);
    }

    private String createInvoiceHtml(Invoice invoice) {
        return """
                <html>
                <body>
                <h2>Invoice #%s</h2>
                <p>User: %s %s</p>
                <p>Amount: %s %s</p>
                <p>Status: %s</p>
                <p>Rental ID: %s</p>
                <p>Created At: %s</p>
                </body>
                </html>
                """.formatted(
                invoice.getInvoiceNumber(),
                invoice.getUser().getFirstName(), invoice.getUser().getLastName(),
                invoice.getAmount(), invoice.getCurrency(),
                invoice.getStatus(),
                invoice.getRental().getId(),
                invoice.getCreatedAt()
        );
    }

    private byte[] generateInvoicePdf(Invoice invoice) {
        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);

            document.open();
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("Invoice #" + invoice.getInvoiceNumber(), titleFont));
            document.add(new Paragraph("User: " + invoice.getUser().getFirstName() + " " + invoice.getUser().getLastName(), normalFont));
            document.add(new Paragraph("Amount: " + invoice.getAmount() + " " + invoice.getCurrency(), normalFont));
            document.add(new Paragraph("Status: " + invoice.getStatus(), normalFont));
            document.add(new Paragraph("Rental ID: " + invoice.getRental().getId(), normalFont));
            document.add(new Paragraph("Created At: " + invoice.getCreatedAt(), normalFont));

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            log.error("Failed to generate invoice PDF", e);
            return new byte[0];
        }
    }
}
