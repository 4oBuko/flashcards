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
        // todo:get letter from message builder and send it
    }

    public void sendEmailUpdateLetter(User user) {
        // todo: I can delete this method and use only one
        // todo: get letter from message builder and send it
    }
}
