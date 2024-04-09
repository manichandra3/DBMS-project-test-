package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	Optional<Employee> authenticate(String employeeContact, String password);
}
