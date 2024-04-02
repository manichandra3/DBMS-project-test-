package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    EmployeeService employeeService;
    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

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

    @GetMapping("/employee/login")
    public String showEmployeeLoginForm(Model model) {
        return "employee_login";
    }

    @PostMapping("/employee/login")
    public String employeeLogin(@RequestParam Long employeeId, @RequestParam String password, Model model) {
        Optional<Employee> employee = employeeService.authenticate(employeeId, password);

        if (employee.isPresent()) {
            // Authentication successful
            // add the employee object to the session or model for further use
            model.addAttribute("employee", employee);
            return "redirect:/employee/profile/"+employee.get().getId(); // Return the view for the employee dashboard
        } else {
            // Authentication failed
            model.addAttribute("error", "Invalid Employee ID or Password");
            return "employee_login";
        }
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
