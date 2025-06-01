package com.example.libraryapi.security;

import com.example.libraryapi.model.User;
import com.example.libraryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    public final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("🔍 Attempting login for email: " + email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("❌ User not found with email: " + email);
                    return new UsernameNotFoundException("User not found with email: "
                            + email);
                });

        System.out.println("✅ User found: " + user.getEmail());
        System.out.println("🔐 Password hash in DB: " + user.getPassword());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                // use authorities instead of roles not to get ROLE_ROLE_USER
                .authorities(user.getRole())
                .build();
    }
}
