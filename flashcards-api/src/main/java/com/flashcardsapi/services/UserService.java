package com.flashcardsapi.services;

import com.flashcardsapi.dtos.user.CreateUserDTO;
import com.flashcardsapi.dtos.user.UpdateUserCredentialDTO;
import com.flashcardsapi.entities.JwtPayload;
import com.flashcardsapi.exceptions.AlreadyUsedCredentialsException;
import com.flashcardsapi.exceptions.CustomEntityNotFoundException;
import com.flashcardsapi.utils.JwtPayloadReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.flashcardsapi.entities.db.User;
import com.flashcardsapi.entities.db.VerificationToken;
import com.flashcardsapi.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenService jwtTokenService;

    private final EmailService emailService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenService jwtTokenService, EmailService emailService, @Value("${email.verification.enabled}") Boolean emailVerificationEnabled) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
        this.emailService = emailService;
        this.emailVerificationEnabled = emailVerificationEnabled;
    }

    private final boolean emailVerificationEnabled;

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Transactional
    public void registerNewUser(CreateUserDTO createUserDTO) {
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
            userRepository.save(user);
            if (emailVerificationEnabled) {
                user.setConfirmed(false);
                emailService.sendVerificationLetter(user);
            } else {
                user.setConfirmed(true);
            }
        }
    }

    public User getUserByToken(String token) {
        Long userId = (Long) jwtTokenService.getTokenClaims(token).get("id");
        if (userId == null) {
            return null;
        }
        return getById(userId);
    }

    public User updatePassword(UpdateUserCredentialDTO credentialDTO, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        String encodedNewPassword = passwordEncoder.encode(credentialDTO.getCredential());
        User user = userRepository.findById(payload.getUserId()).orElseThrow(CustomEntityNotFoundException::new);
        user.setPassword(encodedNewPassword);
        return userRepository.save(user);
    }

    public User updateEmail(UpdateUserCredentialDTO credentialDTO, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        User user = userRepository.findById(payload.getUserId()).orElseThrow(CustomEntityNotFoundException::new);
        user.setEmail(credentialDTO.getCredential());
        if (emailVerificationEnabled) {
            user.setConfirmed(false);
            emailService.sendEmailUpdateLetter(user);
        }
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User updateNickname(UpdateUserCredentialDTO credentialDTO, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        User user = userRepository.findById(payload.getUserId()).orElseThrow(CustomEntityNotFoundException::new);
        if (isNicknameAvailable(credentialDTO.getCredential())) {
            user.setNickname(credentialDTO.getCredential());
            return userRepository.save(user);
        }
        throw new AlreadyUsedCredentialsException("Nickname is already used");
    }

    public boolean isNicknameAvailable(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }

    public void confirmUser(VerificationToken token) {
        User user = token.getUser();
        user.setConfirmed(true);
        userRepository.save(user);
    }

    public User getByNickname(String nickname) {
        return userRepository.findByNickname(nickname).orElseThrow(CustomEntityNotFoundException::new);
    }
}
