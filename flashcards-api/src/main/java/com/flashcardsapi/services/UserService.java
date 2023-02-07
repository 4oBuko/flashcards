package com.flashcardsapi.services;

import com.flashcardsapi.dtos.user.CreateUserDTO;
import com.flashcardsapi.exceptions.AlreadyUsedCredentialsException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flashcardsapi.entities.User;
import com.flashcardsapi.entities.VerificationToken;
import com.flashcardsapi.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

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
        return userRepository.findUserByEmail(email);
    }

    @Transactional
    public User registerNewUser(CreateUserDTO createUserDTO) {
        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            throw new AlreadyUsedCredentialsException("email is already used");
        } else if (userRepository.existsByNickname(createUserDTO.getNickname())) {
            throw new AlreadyUsedCredentialsException("nickname is already used");
        } else {
            User user = new User();
            user.setEmail(createUserDTO.getEmail());
            user.setNickname(createUserDTO.getNickname());
            user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
            user.setRegistrationDate(LocalDate.now());
            user.setConfirmed(true);// todo: change on false after implementing email verification
            return userRepository.save(user);
        }
    }

    public User getUserByToken(String token) {
        Long userId = (Long) jwtTokenService.getTokenClaims(token).get("id");
        if (userId == null) {
            return null;
        }
        return getById(userId);
    }

    public User updatePassword(String newPassword, Long userId) {
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
