package com.flashcardsapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.flashcardsapi.entities.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    public String generateToken(User user) {
        JwtClaimsSet claims = createJwtClaimsSet(user, false);
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(User user) {
        JwtClaimsSet claims = createJwtClaimsSet(user, true);
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean isTokenValid(String token) {
        Map<String, Object> claims = getTokenClaims(token);
        JwtClaimsSet.Builder builder = JwtClaimsSet.builder();
        claims.forEach(builder::claim);
        String generatedToken = jwtEncoder.encode(JwtEncoderParameters.from(builder.build())).getTokenValue();
        return generatedToken.equals(token);
    }

    private JwtClaimsSet createJwtClaimsSet(User user, boolean isRefreshToken) {
        Instant now = Instant.now();
//        if isRefreshToken is true token will be valid for 182 days (half of the year) otherwise for 5 minutes
        Instant validityTime = isRefreshToken ? now.plus(182, ChronoUnit.DAYS) : now.plus(5, ChronoUnit.MINUTES);
        return JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validityTime)
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("nickname", user.getNickname())
                .build();
    }

    public Map<String, Object> getTokenClaims(String token) {
        return jwtDecoder.decode(token)
                .getClaims();
    }

    public Map<String, String> generateTokens(User user) {
        Map<String, String> tokens = new LinkedHashMap<>();
        tokens.put("accessToken", generateToken(user));
        tokens.put("refreshToken", generateRefreshToken(user));
        return tokens;
    }
}
