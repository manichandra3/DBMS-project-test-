package com.thymeleafspringbootapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Customer;
import com.thymeleafspringbootapplication.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new java.util.ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for id :: " + id));
    }

    @Override
    public void deleteCustomerById(long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found for id :: " + id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public Long getLastCustomerId() {
        return customerRepository.findLastCustomerId();
    }

}
