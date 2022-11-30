package com.chornobuk.flashcardsapi.services;

import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.entities.VerificationToken;
import com.chornobuk.flashcardsapi.repositories.VerificationTokenRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private VerificationTokenRepository tokenRepository;
    private static final int LETTER_VALIDITY_DAYS = 1;
    
    public boolean verifyToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token).orElse(null);
        if( verificationToken == null)  {
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
        String token = "";//todo:generate token
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setCreatedAt(LocalDateTime.now());
        verificationToken.setExpiresAt(LocalDateTime.now().plusDays(LETTER_VALIDITY_DAYS));
        return token;
    }
}
