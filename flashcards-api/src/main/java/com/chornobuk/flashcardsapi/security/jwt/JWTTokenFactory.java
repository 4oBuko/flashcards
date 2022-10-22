package com.chornobuk.flashcardsapi.security.jwt;

import com.chornobuk.flashcardsapi.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenFactory {
    //    todo:replace with more secure secret after jwt implementation
    private final static String secret = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQSflKxwRJSMeKKF2QT4fwpMeJf36POk6yJVadQssw5dc";
//    String secretString = Encoders.BASE64.encode(key.getEncoded());
//    private final static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long TOKEN_VALIDITY = 24 * 60 * 60; //one day validity
    private static final long REFRESH_TOKEN_VALIDITY = 182 * 24 * 60 * 60; // half of the year validity

    private String generateToken(long validityTime, User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validityTime * 1000L))
                .signWith(SignatureAlgorithm.HS256, secret)//todo: replace deprecated method
                .compact();
    }

    public String generateToken(User user) {
        return generateToken(TOKEN_VALIDITY, user);
    }

    public String generateRefreshTokne(User user) {
        return generateToken(REFRESH_TOKEN_VALIDITY, user);
    }
}
