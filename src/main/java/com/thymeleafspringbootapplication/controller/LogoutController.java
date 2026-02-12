package com.thymeleafspringbootapplication.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @PostMapping("/employee/logout")
    public ResponseEntity<String> employeeLogout(HttpSession session) {
        // Invalidate the session to log out the user
        session.invalidate();

        // Return a response indicating successful logout
        return ResponseEntity.ok("Logged out successfully");
    }
}
