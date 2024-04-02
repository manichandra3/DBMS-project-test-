package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.model.LoginRequest;
import com.thymeleafspringbootapplication.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    EmployeeService employeeService;
    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/employee/login")
    public String showEmployeeLoginForm(Model model) {
        return "employee_login";
    }

    @PostMapping("/employee/login")
    public ResponseEntity<Optional<Employee>> employeeLogin(@RequestBody LoginRequest request) {
        // Extract employeeContact and password from the request object
        String employeeContact = request.getEmployeeContact();
        String password = request.getPassword();

        // Perform authentication using employeeContact and password
        Optional<Employee> employee = employeeService.authenticate(employeeContact, password);

        // Return ResponseEntity with JSON data
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
