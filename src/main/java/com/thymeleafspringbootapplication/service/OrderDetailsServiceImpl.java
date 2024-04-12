package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.OrderDetails;
import com.thymeleafspringbootapplication.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

    private final OrderDetailsRepository orderDetailsRepository;
    @Autowired
    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        List<OrderDetails> orderDetails = new ArrayList<>();
        Iterable<OrderDetails> iterable = orderDetailsRepository.findAll();
        iterable.forEach(orderDetails::add);
        return orderDetails;
    }

    @Override
    public OrderDetails getOrderDetailsById(Long id) {
        Optional<OrderDetails> orderDetails = orderDetailsRepository.findById(id);
        if (orderDetails.isPresent()) {
            return orderDetails.get();
        } else {
            throw new RuntimeException("Order not found for id " + id);
        }
    }
}
