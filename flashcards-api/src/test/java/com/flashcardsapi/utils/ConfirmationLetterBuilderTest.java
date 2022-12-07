package com.flashcardsapi.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.flashcardsapi.entities.User;
import com.flashcardsapi.entities.VerificationToken;

public class ConfirmationLetterBuilderTest {

    User testUser = new User();
    VerificationToken token = new VerificationToken();

    {
        testUser.setNickname("test");
        testUser.setEmail("testmail@test.com");

        token.setUser(testUser);
        token.setToken("asdfwpnsodjf132");
    }

    @Test
    void testGenerateEmailUpdateLetter() {
        String correctMessage = """
                Hi test, your email was updated to testmail@test.com. To set new email, follow the link bellow:
                asdfwpnsodjf132
                """;
        assertEquals(correctMessage, ConfirmationLetterBuilder.generateEmailUpdateLetter(token));
    }

    @Test
    void testGenerateRegistrationLetter() {
        String correctMessage = """
            Hi test, you registered on site. Activate your account by the link bellow:
            asdfwpnsodjf132
            """;
        assertEquals(correctMessage, ConfirmationLetterBuilder.generateRegistrationLetter(token));
    }
}
