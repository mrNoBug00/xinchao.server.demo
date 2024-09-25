package com.xinchao.controllers;

import com.xinchao.dto.SignupDTO;
import com.xinchao.models.User;
import com.xinchao.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/auth")
@RestController
public class CreateAdminController {

    private final UserService userService;

    public CreateAdminController(UserService userService) {
        this.userService = userService;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/create/admins")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> createAdministrator(@RequestBody SignupDTO registerUserDto) {
        User createdAdmin = userService.createAdministrator(registerUserDto);
        return ResponseEntity.ok(createdAdmin);
    }
}