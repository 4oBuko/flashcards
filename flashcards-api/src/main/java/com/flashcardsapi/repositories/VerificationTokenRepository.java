package com.flashcardsapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.flashcardsapi.entities.db.VerificationToken;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
