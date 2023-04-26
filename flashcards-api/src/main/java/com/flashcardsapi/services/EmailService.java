package com.flashcardsapi.services;

import com.flashcardsapi.exceptions.CustomEntityNotFoundException;
import com.flashcardsapi.utils.ConfirmationLetterBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.flashcardsapi.entities.db.User;
import com.flashcardsapi.entities.db.VerificationToken;
import com.flashcardsapi.repositories.VerificationTokenRepository;


@Service
public class EmailService {
    private static final int LETTER_VALIDITY_DAYS = 1;
    private final VerificationTokenRepository tokenRepository;

    private final JavaMailSender mailSender;
    private final String username;


    public EmailService( @Value("${spring.mail.username}") String username, VerificationTokenRepository tokenRepository, JavaMailSender mailSender) {
        this.tokenRepository = tokenRepository;
        this.mailSender = mailSender;
        this.username = username;
    }

    public VerificationToken getToken(String token) {
        return tokenRepository.findByToken(token).orElse(null);
    }

    public boolean verifyToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token).orElseThrow(CustomEntityNotFoundException::new);
        return !verificationToken.getExpiresAt().isBefore(LocalDateTime.now());
    }

    public void sendVerificationLetter(User user) {
        VerificationToken verificationToken = createTokenByUser(user);
        tokenRepository.save(verificationToken);

        String letter = ConfirmationLetterBuilder.generateRegistrationLetter(verificationToken);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(verificationToken.getUser().getEmail());
        message.setSubject("Flashcards Email verification");
        message.setText(letter);
        mailSender.send(message);
    }

    public void sendEmailUpdateLetter(User user) {
        VerificationToken verificationToken = createTokenByUser(user);
        tokenRepository.save(verificationToken);
    }

    private VerificationToken createTokenByUser(User user) {
        VerificationToken verificationToken = new VerificationToken();
        String token = UUID.randomUUID().toString();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setCreatedAt(LocalDateTime.now());
        verificationToken.setExpiresAt(LocalDateTime.now().plusDays(LETTER_VALIDITY_DAYS));
        return verificationToken;
    }
}
