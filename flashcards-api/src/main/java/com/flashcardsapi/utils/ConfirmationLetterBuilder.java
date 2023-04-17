package com.flashcardsapi.utils;

import com.flashcardsapi.entities.db.VerificationToken;

public class ConfirmationLetterBuilder {

    // @Value("${domain.name}")//todo:add domain
    private final static String DOMAIN ="http://localhost:8080/register/confirm";

    public static String generateRegistrationLetter(VerificationToken token) {
        String message = """
                Hi %s, you registered on %s. Activate your account by the link bellow:
                %s
                """.formatted(token.getUser().getNickname(), "site", generateURL(token.getToken()));
        return message;
    }

    public static String generateEmailUpdateLetter(VerificationToken token) {
        String message = """
                Hi %s, your email was updated to %s. To set new email, follow the link bellow:
                %s
                """.formatted(token.getUser().getNickname(), token.getUser().getEmail(), generateURL(token.getToken()));
        return message;
    }

    public static String generateURL(String token) {
        return DOMAIN + "?token=" + token;
    }
}
