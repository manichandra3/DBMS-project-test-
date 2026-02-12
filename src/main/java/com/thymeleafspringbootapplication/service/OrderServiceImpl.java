package com.thymeleafspringbootapplication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Customer;
import com.thymeleafspringbootapplication.model.Order;
import com.thymeleafspringbootapplication.repository.CustomerRepository;
import com.thymeleafspringbootapplication.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        Iterable<Order> iterable = orderRepository.findAll();
        List<Order> orderList = new ArrayList<>();
        iterable.forEach(orderList::add);
        return orderList;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for id :: " + id));
    }

    @Override
    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for id :: " + id));
    }

    @Override
    public Long getLastOrderId() {
        return orderRepository.findLastOrderId();
    }

    @Override
    public Long getOrderTotalSum() {
        return orderRepository.findTotalOrders();
    }
}