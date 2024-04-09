package com.thymeleafspringbootapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thymeleafspringbootapplication.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
