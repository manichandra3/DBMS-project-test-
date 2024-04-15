package com.thymeleafspringbootapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCustomers'");
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        Customer customer;
        if(optional.isPresent()) {
            customer = optional.get();
        } else {
            throw new RuntimeException("Customer not found for Id :: " + id);
        }
        return customer;
    }

    @Override
    public void deleteCustomerById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomerById'");
    }

    @Override
    public Long getLastCustomerId() {
        return customerRepository.findLastCustomerId();
    }

}
