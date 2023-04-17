package com.flashcardsapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.flashcardsapi.entities.db.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
