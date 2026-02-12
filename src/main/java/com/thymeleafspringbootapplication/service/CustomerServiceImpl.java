package com.thymeleafspringbootapplication.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Customer;
import com.thymeleafspringbootapplication.repository.CustomerRepository;
import com.thymeleafspringbootapplication.repository.PaysRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final PaysRepository paysRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, PaysRepository paysRepository) {
        this.customerRepository = customerRepository;
        this.paysRepository = paysRepository;
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
    @Transactional
    public void deleteCustomerById(long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found for id :: " + id);
        }
        logger.info("Deleting customer with id {} and its related records", id);
        paysRepository.deleteByCustomerId(id);
        customerRepository.deleteById(id);
        logger.info("Successfully deleted customer with id {}", id);
    }

    @Override
    public Long getLastCustomerId() {
        return customerRepository.findLastCustomerId();
    }

}
