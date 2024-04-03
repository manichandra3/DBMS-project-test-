package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //	Show all employees
    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees(Employee employee) {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeList);
    }

    //	Add new employee.
    @PostMapping("/saveEmployee")
    public ResponseEntity<String> saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok("Employee successfully saved to the Database");
    }
    //	Delete employee.
    @GetMapping("/deleteEmployee/{id}")
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