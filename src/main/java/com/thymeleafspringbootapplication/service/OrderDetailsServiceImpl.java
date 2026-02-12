package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.OrderDetails;
import com.thymeleafspringbootapplication.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

    private final OrderDetailsRepository orderDetailsRepository;

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
        return orderDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order details not found for id :: " + id));
    }

    @Override
    public Long getLastOrderDid() {
        return orderDetailsRepository.findLastOrderDid();
    }

    @Override
    public void saveOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    @Override
    public Long getTotalOrderQuantity(Long productId) {
        return orderDetailsRepository.sumProductQuantityByProductId(productId);
    }
}
