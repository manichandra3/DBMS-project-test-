package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.model.LoginRequest;
import com.thymeleafspringbootapplication.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    EmployeeService employeeService;
    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee/login")
    public ResponseEntity<Optional<Employee>> employeeLogin(@RequestBody LoginRequest request, HttpSession session) {
        // Extract employeeContact and password from the request object
        String employeeContact = request.getEmployeeContact();
        String password = request.getPassword();

        // Perform authentication using employeeContact and password
        Optional<Employee> employee = employeeService.authenticate(employeeContact, password);

        // Return ResponseEntity with JSON data
        if (employee.isPresent()) {
            session.setAttribute("loggedInUser", employee.get());
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
