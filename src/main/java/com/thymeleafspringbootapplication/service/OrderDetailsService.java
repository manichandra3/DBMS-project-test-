package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.OrderDetails;

import java.util.List;

public interface OrderDetailsService {
    List<OrderDetails> getAllOrderDetails();
    OrderDetails getOrderDetailsById(Long id);
}
