package com.thymeleafspringbootapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.service.EmployeeService;
// Admin controller routes all the incoming HTTP requests to appropriate methods and pages.
// Admin manages Employees.
@Controller
public class AdminController {
	
	@Autowired
	private EmployeeService employeeService;


// Display details of all the employees in the DB.
	@GetMapping("/admin")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "admin";
	}
//	Display the employee details form for new employee.
	@GetMapping("/admin/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// Create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
//	Add new employee.
	@PostMapping("/admin/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/admin";
	}
//	Display the employee details form for employee update.
	@GetMapping("/admin/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
	}
//	delete employee.
	@GetMapping("/admin/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/admin";
	}

}
