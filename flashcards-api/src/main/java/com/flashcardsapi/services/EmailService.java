package com.flashcardsapi.services;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.flashcardsapi.entities.User;
import com.flashcardsapi.entities.VerificationToken;
import com.flashcardsapi.repositories.VerificationTokenRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService {
    private VerificationTokenRepository tokenRepository;
    private static final int LETTER_VALIDITY_DAYS = 1;
    private final JavaMailSender mailSender;

    public boolean verifyToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token).orElse(null);
        if (verificationToken == null) {
            return false;
        }
        if (verificationToken.getExpiresAt().isAfter(LocalDateTime.now())) {
            sendVerificationLetter(verificationToken.getUser());
            return false;
        }
        return true;
    }

    public void sendVerificationLetter(User user) {
        VerificationToken verificationToken = createTokenByUser(user);
        tokenRepository.save(verificationToken);
        // todo:get letter from message builder and send it
    }

    public void sendEmailUpdateLetter(User user) {
        VerificationToken verificationToken = createTokenByUser(user);
        tokenRepository.save(verificationToken);
        // todo: I can delete this method and use only one
        // todo: get letter from message builder and send it
    }

    private VerificationToken createTokenByUser(User user) {
        VerificationToken verificationToken = new VerificationToken();
        String token = "";// todo:generate token
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setCreatedAt(LocalDateTime.now());
        verificationToken.setExpiresAt(LocalDateTime.now().plusDays(LETTER_VALIDITY_DAYS));
        return verificationToken;
    }

    private void sendEmail(String email, String to, String subject) {
        try {
            // MimeMessage mimeMessage = mailSender.createMimeMessage();
            // MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            // mimeMessageHelper.setText(email, true);
            // mimeMessageHelper.setTo(to);
            // mimeMessageHelper.setSubject(subject);
            // mimeMessageHelper.setFrom("buyracktar.app@test.com");
            // mailSender.send(mimeMessage);
        } catch (MessagingException e) {

        }
    }
}
