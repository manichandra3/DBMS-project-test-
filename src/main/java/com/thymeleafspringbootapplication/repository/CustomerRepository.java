package com.thymeleafspringbootapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thymeleafspringbootapplication.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
