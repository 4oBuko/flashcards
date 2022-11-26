package com.chornobuk.flashcardsapi.repositories;

import com.chornobuk.flashcardsapi.entities.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    List<User> findAllByNickname(String nickname);
}
