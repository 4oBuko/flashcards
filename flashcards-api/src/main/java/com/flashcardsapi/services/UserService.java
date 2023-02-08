package com.flashcardsapi.services;

import com.flashcardsapi.dtos.user.CreateUserDTO;
import com.flashcardsapi.dtos.user.UpdateUserCredentialDTO;
import com.flashcardsapi.exceptions.AlreadyUsedCredentialsException;
import com.flashcardsapi.exceptions.CustomEntityNotFoundException;
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
        return userRepository.findById(id).orElseThrow(CustomEntityNotFoundException::new);
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

    public User updatePassword(UpdateUserCredentialDTO credentialDTO) {
        String encodedNewPassword = passwordEncoder.encode(credentialDTO.getCredential());
        User user = userRepository.findById(credentialDTO.getId()).orElseThrow(CustomEntityNotFoundException::new);
        user.setPassword(encodedNewPassword);
        return userRepository.save(user);
    }

    public User updateEmail(UpdateUserCredentialDTO credentialDTO) {
        User user = userRepository.findById(credentialDTO.getId()).orElseThrow(CustomEntityNotFoundException::new);
        user.setEmail(credentialDTO.getCredential());
        user.setConfirmed(false);
        userRepository.save(user);
        emailService.sendEmailUpdateLetter(user);
        return user;
    }

    @Transactional
    public User updateNickname(UpdateUserCredentialDTO credentialDTO) {
        User user = userRepository.findById(credentialDTO.getId()).orElseThrow(CustomEntityNotFoundException::new);
        user.setNickname(credentialDTO.getCredential());
        return userRepository.save(user);
    }

    public boolean isNicknameAvailable(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public void confirmUser(VerificationToken token) {
        User user = token.getUser();
        user.setConfirmed(true);
        // I can delete token after using
        user.setConfirmed(true);
        userRepository.save(user);
    }
}
