package com.thymeleafspringbootapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
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
		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for id :: " + id));
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
