package com.chornobuk.flashcardsapi.services;

import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
