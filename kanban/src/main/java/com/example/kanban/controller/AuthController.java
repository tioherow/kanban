package com.example.kanban.controller;

import com.example.kanban.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
    }
}

class LoginRequest {
    private String username;
    private String password;

    // Getters and setters
}
