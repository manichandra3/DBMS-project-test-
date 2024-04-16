package com.thymeleafspringbootapplication.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thymeleafspringbootapplication.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "SELECT ORDERS.order_id FROM ORDERS ORDER BY ORDERS.order_id desc limit 1", nativeQuery = true)
    Long findLastOrderId();
    @Query(value =  "SELECT SUM(ORDERS.order_total) FROM ORDERS", nativeQuery = true)
    Long findTotalOrders();
}
