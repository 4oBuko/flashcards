package com.flashcardsapi.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flashcardsapi.entities.User;
import com.flashcardsapi.entities.VerificationToken;
import com.flashcardsapi.repositories.UserRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenService jwtTokenService;
    private EmailService emailService;

    public User getById(Long id) throws IllegalArgumentException {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    public User registerNewUser(User newUser) {
        // todo check is email and nickname are available
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRegistrationDate(LocalDate.now());
        // todo check is
        newUser.setConfirmed(true);// todo: change on false after implementing email verification
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
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        user.setPassword(encodedNewPassword);
        return userRepository.save(user);
    }

    public User updateEmail(Long userId, String newEmail) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        user.setEmail(newEmail);
        user.setConfirmed(false);
        userRepository.save(user);
        emailService.sendEmailUpdateLetter(user);
        return user;
    }

    public User updateNickname(Long userId, String newNickname) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        user.setNickname(newNickname);
        return userRepository.save(user);
    }

    public boolean isNicknameAvailable(String nickname) {
        return userRepository.findAllByNickname(nickname).size() == 0;
    }

    public void confirmUser(VerificationToken token) {
        User user = token.getUser();
        user.setConfirmed(true);
        // I can delete token after using
        user.setConfirmed(true);
        userRepository.save(user);
    }
}
