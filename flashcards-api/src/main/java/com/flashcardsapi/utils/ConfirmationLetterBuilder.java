package com.flashcardsapi.utils;

import com.flashcardsapi.entities.VerificationToken;

public class ConfirmationLetterBuilder {
    public static String generateRegistrationLetter(VerificationToken token) {
        String message = """
                Hi %s, you registered on %s. Activate your account by the link bellow:
                %s
                """.formatted(token.getUser().getNickname(), "site", token.getToken());
        return message;

    }

    public static String generateEmailUpdateLetter(VerificationToken token) {
        String message = """
                Hi %s, your email was updated to %s. To set new email, follow the link bellow:
                %s
                """.formatted(token.getUser().getNickname(), token.getToken());
        return message;
    }
}
