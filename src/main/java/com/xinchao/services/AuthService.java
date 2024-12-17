package com.xinchao.services;

import com.xinchao.dto.LoginDto;
import com.xinchao.exception.InvalidPasswordException;
import com.xinchao.exception.InvalidUsernameException;
import com.xinchao.models.User;
import com.xinchao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public User authenticate(String identifier, String password) {
        Optional<User> userOpt = findUserByIdentifier(identifier);

        if (userOpt.isEmpty()) {
            throw new InvalidUsernameException("Invalid account");
        }

        User user = userOpt.get();

        // Kiểm tra mật khẩu
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        return user; // Trả về đối tượng User nếu xác thực thành công
    }

    private Optional<User> findUserByIdentifier(String identifier) {
        // Kiểm tra các trường khác nhau dựa trên identifier
        Optional<User> user = userRepository.findByEmail(identifier);
        if (user.isPresent()) {
            return user;
        }

        user = userRepository.findByVnId(identifier);
        if (user.isPresent()) {
            return user;
        }

        user = userRepository.findByArc(identifier);
        if (user.isPresent()) {
            return user;
        }

        user = userRepository.findByPassportNumber(identifier);
        if (user.isPresent()) {
            return user;
        }

        user = userRepository.findByPhoneNumber(identifier);
        return user; // Trả về Optional.empty() nếu không tìm thấy
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
