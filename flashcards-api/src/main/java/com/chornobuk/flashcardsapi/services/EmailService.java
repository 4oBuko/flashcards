package com.chornobuk.flashcardsapi.services;

import com.chornobuk.flashcardsapi.entities.User;
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

    public void sendVerificationLetter(User user) {
//    todo:get letter from message builder and send it
    }
    public void sendEmailUpdateLetter(User user) {
        // todo: get letter from message builder and send it
    }
}
