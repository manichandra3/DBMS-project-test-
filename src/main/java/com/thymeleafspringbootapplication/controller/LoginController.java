package com.thymeleafspringbootapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @PostMapping("/admin/login")
    public String adminLogin(String username, String password, Model model) {
        // Perform database validation here
        // You can use Spring Data JPA, JDBC, or any other method to validate the credentials

        // For demonstration purposes, let's assume a simple validation
        if ("admin".equals(username) && "password".equals(password)) {
            // Authentication successful
            return "redirect:/admin";
        } else {
            // Authentication failed
            model.addAttribute("error", "Invalid username or password");
            return "admin_login";
        }
    }

    @PostMapping("/employee/login")
    public String employeeLogin(String username, String password, Model model) {
        // Perform database validation here
        // You can use Spring Data JPA, JDBC, or any other method to validate the credentials

        // For demonstration purposes, let's assume a simple validation
        if ("admin".equals(username) && "password".equals(password)) {
            // Authentication successful
            return "redirect:/employee/";
        } else {
            // Authentication failed
            model.addAttribute("error", "Invalid username or password");
            return "employee_login";
        }
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
