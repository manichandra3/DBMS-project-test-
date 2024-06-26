package com.thymeleafspringbootapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee;
		if (optional.isPresent()) {
			employee =  optional.get();
		} else {
			throw new RuntimeException("Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
	}

	@Override
	public Optional<Employee> authenticate(String employeeContact, String password) {
		// Retrieve the employee from the database based on the employeeId
		Optional<Employee> employee = employeeRepository.findByContact(employeeContact);

		// Check if the employee exists and the password matches
		if (employee.isPresent() && employee.get().getPassword().equals(password)) {
			return employee;
		}
		return Optional.empty();
	}

}
