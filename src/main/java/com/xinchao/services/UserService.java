package com.xinchao.services;

import com.xinchao.dto.LoginDto;
import com.xinchao.dto.SignupDTO;
import com.xinchao.models.Role;
import com.xinchao.models.RoleEnum;
import com.xinchao.models.User;
import com.xinchao.payload.request.UserRequest;
import com.xinchao.payload.response.UserResponse;
import com.xinchao.repository.RoleRepository;
import com.xinchao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;




    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }



    public List<UserResponse> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<User> userList = StreamSupport
                .stream(users.spliterator(), false)
                .collect(Collectors.toList());

        return userList.stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getArc(),
                user.getVnId(),
                user.getPassportNumber(),
                user.getRole(),
                user.getLineId(),
                user.getNumberZalo()
        );
    }

    public User createAdministrator(SignupDTO input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User()
                .setUserName(input.getUsername())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get());

        return userRepository.save(user);
    }

    public UserResponse createUser(UserRequest userRequest) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        if (optionalRole.isEmpty()) {
            return null; // or handle the error case appropriately
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setPhoneNumber(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
        user.setRole(optionalRole.get());
        user.setArc(userRequest.getArc());
        user.setVnId(userRequest.getVnId());
        user.setPassportNumber(userRequest.getPassport());
        user.setLineId(userRequest.getLineId());
        user.setNumberZalo(userRequest.getNumberZalo());

        User savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);
    }



    public UserResponse updateUser(UserRequest userRequest, String id) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
        user.setArc(userRequest.getArc());
        user.setVnId(userRequest.getVnId());
        user.setPassportNumber(userRequest.getPassport());
        user.setLineId(userRequest.getLineId());
        user.setNumberZalo(userRequest.getNumberZalo());

        User savedUser = userRepository.save(user);
        return mapToUserResponse(savedUser);
    }



    public String deleteUser(String userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        userRepository.delete(existingUser);
        return "User deleted";
    }
}