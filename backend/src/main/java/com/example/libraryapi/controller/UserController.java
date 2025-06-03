package com.example.libraryapi.controller;

import com.example.libraryapi.dto.UserActivityDTO;
import com.example.libraryapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get user activity - role and book count",
            description = "Returns a list of users along with their roles and the number of books they have fetched.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User activity data retrieved successfully")
    })
    // GET /api/users/activity-role
    @GetMapping("/activity-role")
    public List<UserActivityDTO> getUserRoleAndBookCount() {
        return userService.getUserRoleAndBookCount();
    }
}
