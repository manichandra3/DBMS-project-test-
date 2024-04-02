package com.thymeleafspringbootapplication.service;

import java.util.List;
import java.util.Optional;

import com.thymeleafspringbootapplication.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	Optional<Employee> authenticate(long employeeId, String password);
}
