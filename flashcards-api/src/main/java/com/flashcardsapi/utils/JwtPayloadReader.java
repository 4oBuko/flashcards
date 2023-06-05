package com.flashcardsapi.utils;

import com.flashcardsapi.entities.JwtPayload;
import org.springframework.security.oauth2.jwt.Jwt;

public class JwtPayloadReader {
    public static JwtPayload getPayload(Jwt jwt) {
        JwtPayload payload = new JwtPayload();
        payload.setUserId(jwt.getClaim("id"));
        payload.setNickname(jwt.getClaimAsString("nickname"));
        payload.setEmail(jwt.getClaimAsString("email"));
        return payload;
    }
}
