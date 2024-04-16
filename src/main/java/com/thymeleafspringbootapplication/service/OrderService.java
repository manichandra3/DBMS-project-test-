package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Customer;
import com.thymeleafspringbootapplication.model.Order;

import java.util.List;


public interface OrderService {

    List<Order> getAllOrders();

    void save(Order order);

    Order getOrderById(long id);

    void deleteOrderById(long id);

    Customer getCustomerById(long id);

    Long getLastOrderId();

    Long getOrderTotalSum();

}
