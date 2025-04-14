package com.hanhwa_tae.secondserver.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailUtil {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String targetEmail, String subject, String contents) throws MessagingException {
        MimeMessage message =javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, targetEmail);
        message.setSubject(subject);
        message.setText(contents, "UTF-8", "html");

        javaMailSender.send(message);
    }
}
