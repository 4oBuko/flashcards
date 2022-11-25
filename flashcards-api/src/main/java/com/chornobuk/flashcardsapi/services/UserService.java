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

    public User updatePassoword(String newPassword, Long userId) {
        // I can get userId from jwt token or as a request param
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            return null;
        }
        user.setPassword(encodedNewPassword);
        return userRepository.save(user);
    }

    public boolean updateEmail(String newEmail, Long userId) {
        // User user = userRepository.findById(userId).orElse(null);
        // todo: get user from db, if null return false or throw an exception
        // if user exists set new email and set confirmed to false
        // send confirmation letter to the new email address
        // method returns logical value if email was updated
        return false;
    }

    public User updateNickname(String newNickname, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            return null;
        }
        user.setNickname(newNickname);
        return userRepository.save(user);
    }

    public boolean isNicknameAvailable(String nickname) {
        // check if the nickname is available and return resultof the search 
        return false;
    }
}
