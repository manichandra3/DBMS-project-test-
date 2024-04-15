package com.thymeleafspringbootapplication.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thymeleafspringbootapplication.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Query(value = "SELECT CUSTOMER.customer_id FROM CUSTOMER ORDER BY CUSTOMER.customer_id desc limit 1", nativeQuery = true)
    Long findLastCustomerId();
}
