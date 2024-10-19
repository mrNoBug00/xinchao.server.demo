package com.xinchao.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinchao.dto.LoginDto;
import com.xinchao.exception.InvalidCredentialsException;
import com.xinchao.exception.UserNotFoundException;
import com.xinchao.models.Role;
import com.xinchao.models.User;
import com.xinchao.payload.request.LoginRequest;
import com.xinchao.payload.request.ProductRequest;
import com.xinchao.payload.request.UserRequest;
import com.xinchao.payload.response.LoginResponse;
import com.xinchao.payload.response.ProductResponse;
import com.xinchao.payload.response.UserResponse;
import com.xinchao.services.AuthService;
import com.xinchao.services.JwtService;
import com.xinchao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final JwtService jwtService;

    private final AuthService authService;

    private final UserService userService;

    @Autowired
    public AuthController(JwtService jwtService, AuthService authService, UserService userService) {
        this.jwtService = jwtService;
        this.authService = authService;
        this.userService = userService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) throws IOException {
        UserResponse registerUser = userService.createUser(userRequest);
        if (registerUser == null) {
            return ResponseEntity.badRequest().body("Failed to register user.");
        }
        return ResponseEntity.ok(registerUser);
    }

    //@CrossOrigin(origins = "https://xinchao-client-demo.vercel.app")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String identifier = loginRequest.getIdentifier(); // Lấy identifier
        String password = loginRequest.getPassword();

        // Kiểm tra xem identifier là email, phone hay ID khác và xác thực
        User authenticatedUser = authService.authenticate(identifier, password);

        // Nếu người dùng không hợp lệ, trả về phản hồi không được phép
        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Tạo JWT Token
        String jwtToken = jwtService.generateToken(authenticatedUser);
        String username = authenticatedUser.getUserName();
        String userId = authenticatedUser.getId();
        Role role = authenticatedUser.getRole();

        // Tạo phản hồi chứa token và thông tin người dùng
        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime())
                .setUserName(username)
                .setRole(role)
                .setUserId(userId);

        // Trả về phản hồi với thông tin đã chuẩn bị
        return ResponseEntity.ok(loginResponse);
    }




    //@CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestParam("user") String userJson,
                                           @PathVariable String id) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        UserRequest userRequest = objectMapper.readValue(userJson, UserRequest.class);

        UserResponse updateUser = userService.updateUser(userRequest, id);
        return ResponseEntity.ok(updateUser);

    }
    //@CrossOrigin(origins = "*")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}