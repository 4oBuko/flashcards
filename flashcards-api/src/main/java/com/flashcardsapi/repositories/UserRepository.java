package com.flashcardsapi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flashcardsapi.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    List<User> findAllByNickname(String nickname);
}
