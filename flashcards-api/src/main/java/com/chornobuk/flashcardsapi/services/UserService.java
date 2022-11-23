package com.chornobuk.flashcardsapi.services;

import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenService jwtTokenService;

    public User getById(Long id) throws IllegalArgumentException {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    public User registerNewUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRegistrationDate(LocalDate.now());
        newUser.setConfirmed(true);//todo: change on false after implementing email verification
        newUser = userRepository.save(newUser);
        return newUser;
    }

    public User getUserByToken(String token) {
        Long userId = (Long) jwtTokenService.getTokenClaims(token).get("id");
        if (userId == null) {
            return null;
        }
        return getById(userId);
    }
}
