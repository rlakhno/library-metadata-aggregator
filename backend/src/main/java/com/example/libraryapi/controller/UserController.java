package com.example.libraryapi.controller;

import com.example.libraryapi.dto.UserActivityDTO;
import com.example.libraryapi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users/activity-role
    @GetMapping("/activity-role")
    public List<UserActivityDTO> getUserRoleAndBookCount() {
        return userService.getUserRoleAndBookCount();
    }
}
