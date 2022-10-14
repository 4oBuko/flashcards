package com.chornobuk.flashcardsapi.repositories;

import com.chornobuk.flashcardsapi.entities.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
}
