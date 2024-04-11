package com.thymeleafspringbootapplication.service;

import java.util.List;

import com.thymeleafspringbootapplication.model.Customer;

public interface CustomerService {
    List<Customer> getAllCustomers();
	void saveCustomer(Customer customer);
	Customer getCustomerById(long id);
	void deleteCustomerById(long id);
}
