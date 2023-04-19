package com.flashcardsapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.flashcardsapi.entities.db.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);

    Optional<User> findByNickname(String nickname);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
