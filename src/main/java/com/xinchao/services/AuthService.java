package com.xinchao.services;

import com.xinchao.dto.LoginDto;
import com.xinchao.models.User;
import com.xinchao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public User authenticate(LoginDto input) {
        User user = findUserByLoginDto(input)
                .orElseThrow(() -> new AuthenticationException("User not found") {});

        String usernameForAuthentication = getUsernameForAuthentication(input, user);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usernameForAuthentication,
                        input.getPassword()
                )
        );

        return user;
    }

    private Optional<User> findUserByLoginDto(LoginDto input) {
        if (input.getEmail() != null && !input.getEmail().isEmpty()) {
            return userRepository.findByEmail(input.getEmail());
        } else if (input.getVnId() != null && !input.getVnId().isEmpty()) {
            return userRepository.findByVnId(input.getVnId());
        } else if (input.getArc() != null && !input.getArc().isEmpty()) {
            return userRepository.findByArc(input.getArc());
        } else if (input.getPassport() != null && !input.getPassport().isEmpty()) {
            return userRepository.findByPassportNumber(input.getPassport());
        } else if (input.getPhone() != null && !input.getPhone().isEmpty()) {
            return userRepository.findByPhoneNumber(input.getPhone());
        }
        return Optional.empty();
    }

    private String getUsernameForAuthentication(LoginDto input, User user) {
        if (input.getEmail() != null && !input.getEmail().isEmpty()) {
            return user.getEmail();
        } else if (input.getVnId() != null && !input.getVnId().isEmpty()) {
            return user.getEmail(); // Assuming email is the username for authentication
        } else if (input.getArc() != null && !input.getArc().isEmpty()) {
            return user.getEmail(); // Assuming email is the username for authentication
        } else if (input.getPassport() != null && !input.getPassport().isEmpty()) {
            return user.getEmail(); // Assuming email is the username for authentication
        } else if (input.getPhone() != null && !input.getPhone().isEmpty()) {
            return user.getEmail(); // Assuming email is the username for authentication
        } else {
            throw new IllegalArgumentException("No valid username field provided for authentication");
        }
    }
}
