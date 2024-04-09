package com.thymeleafspringbootapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @PostMapping("/employee/logout")
    @ResponseBody
    public ResponseEntity<String> employeeLogout(HttpSession session) {
        // Invalidate the session to log out the user
        session.invalidate();

        // Return a response indicating successful logout
        return ResponseEntity.ok("Logged out successfully");
    }
}
