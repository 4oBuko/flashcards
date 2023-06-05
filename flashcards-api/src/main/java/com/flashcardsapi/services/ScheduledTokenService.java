package com.flashcardsapi.services;

import com.flashcardsapi.repositories.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduledTokenService {

//    todo: test this service
    private final VerificationTokenRepository repository;

//    cron expression executes every day at 00:00
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredTokens() {
        repository.deleteAllByExpiresAtBefore(LocalDateTime.now());
    }

}
