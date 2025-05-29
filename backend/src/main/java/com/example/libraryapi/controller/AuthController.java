package com.example.libraryapi.controller;


import com.example.libraryapi.dto.AuthRequest;
import com.example.libraryapi.dto.AuthResponse;
import com.example.libraryapi.model.User;
import com.example.libraryapi.repository.UserRepository;
import com.example.libraryapi.security.CustomUserDetailsService;
import com.example.libraryapi.security.JwtUtil;
import com.example.libraryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserService userService;

    // POST - Register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Check if email already exists
        if(userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        // Default role is USER and creates an immutable Set with just one item ("ROLE_USER")
        user.setRole("ROLE_USER");

        // Register user (password in hashed in UserService)
        userService.registerUser(user);

        return ResponseEntity.ok("User registered successfully");
    }


    // POST - Login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest loginRequest) {

        try{
            // Authenticate user credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest
                            .getEmail(), loginRequest.getPassword())
            );

            // Load user credentials and role for authentication
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());

            // Generate token
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

}
