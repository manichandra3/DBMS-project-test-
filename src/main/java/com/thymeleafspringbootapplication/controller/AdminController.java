package com.thymeleafspringbootapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.service.EmployeeService;

import java.util.List;

// Admin controller routes all the incoming HTTP requests to appropriate methods and pages.
// Admin manages Employees.
@Controller
public class AdminController {
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

}
