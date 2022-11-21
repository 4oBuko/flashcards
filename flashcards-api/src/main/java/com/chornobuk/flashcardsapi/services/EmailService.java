package com.chornobuk.flashcardsapi.services;

import com.chornobuk.flashcardsapi.repositories.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private VerificationTokenRepository tokenRepository;

    public boolean verifyToken(String token) {
        return false;//todo
    }

    public void sendVerificationLetter(String email, String letter) {
//    todo: create email message builder
//        get letter from it and send it to the email
    }
}
