package com.thymeleafspringbootapplication.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.repository.EmployeeRepository;
import com.thymeleafspringbootapplication.repository.MakesRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private final EmployeeRepository employeeRepository;
	private final MakesRepository makesRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, MakesRepository makesRepository) {
		this.employeeRepository = employeeRepository;
		this.makesRepository = makesRepository;
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
	@Transactional
	public void deleteEmployeeById(long id) {
		if (!employeeRepository.existsById(id)) {
			throw new ResourceNotFoundException("Employee not found for id :: " + id);
		}
		logger.info("Deleting employee with id {} and its related records", id);
		makesRepository.deleteByEmployeeId(id);
		employeeRepository.deleteById(id);
		logger.info("Successfully deleted employee with id {}", id);
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
