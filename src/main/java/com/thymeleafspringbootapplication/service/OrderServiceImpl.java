package com.thymeleafspringbootapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thymeleafspringbootapplication.model.Order;
import com.thymeleafspringbootapplication.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        Iterable<Order> iterable =  orderRepository.findAll();
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
        Optional<Order> optional = orderRepository.findById(id);
        Order order;
        if(optional.isPresent()) {
            order = optional.get();
        } else {
            throw new RuntimeException("Order not found for Id :: " + id);
        }
        return order;
    }

    @Override
    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }

}
