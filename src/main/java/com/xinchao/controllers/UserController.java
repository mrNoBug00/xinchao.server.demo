package com.xinchao.controllers;


import com.xinchao.models.User;
import com.xinchao.payload.response.UserResponse;
import com.xinchao.security.AdminPermission;
import com.xinchao.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/users")
    //@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @AdminPermission
    public ResponseEntity<List<UserResponse>> allUsers() {

        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }




}