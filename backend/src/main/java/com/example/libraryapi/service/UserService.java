package com.example.libraryapi.service;

import com.example.libraryapi.model.User;
import com.example.libraryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

   private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*
       private final UserRepository userRepository;
       private final BCryptPasswordEncoder passwordEncoder;

       @Autowired
       public UserService(UserRepository userRepository){
           this.userRepository = userRepository;
           this.passwordEncoder = new BCryptPasswordEncoder();
       }
   */
    public User registerUser(User user) {
        // Check if username already exists
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken");
        }
        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // save to Database
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    // Optional password check helper
    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }


}
