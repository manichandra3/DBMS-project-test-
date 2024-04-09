package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //	Show all employees
    @GetMapping("/showAll")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeList);
    }

    //	Add new employee.
    @PostMapping("/save")
    public ResponseEntity<String> saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok("Employee successfully saved to the Database");
    }
    //	Delete employee.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable (value = "id") long id) {
        this.employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee successfully deleted from the Database");
    }

    //  Get employee with id.
    @GetMapping("/{id}")
    public ResponseEntity<Employee> showEmployeeProfile(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}