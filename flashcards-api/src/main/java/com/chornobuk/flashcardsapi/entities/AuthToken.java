package com.chornobuk.flashcardsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AuthToken {
    private Long id;

    private Long userId;

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;
}



