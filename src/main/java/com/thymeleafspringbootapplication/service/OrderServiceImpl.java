package com.thymeleafspringbootapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Customer;
import com.thymeleafspringbootapplication.model.Has;
import com.thymeleafspringbootapplication.model.Order;
import com.thymeleafspringbootapplication.model.PaysKey;
import com.thymeleafspringbootapplication.repository.CustomerRepository;
import com.thymeleafspringbootapplication.repository.HasRepository;
import com.thymeleafspringbootapplication.repository.MakesRepository;
import com.thymeleafspringbootapplication.repository.OfRepository;
import com.thymeleafspringbootapplication.repository.OrderDetailsRepository;
import com.thymeleafspringbootapplication.repository.OrderRepository;
import com.thymeleafspringbootapplication.repository.PaysRepository;


@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final HasRepository hasRepository;
    private final MakesRepository makesRepository;
    private final OfRepository ofRepository;
    private final PaysRepository paysRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            HasRepository hasRepository, MakesRepository makesRepository,
                            OfRepository ofRepository, PaysRepository paysRepository,
                            OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.hasRepository = hasRepository;
        this.makesRepository = makesRepository;
        this.ofRepository = ofRepository;
        this.paysRepository = paysRepository;
        this.orderDetailsRepository = orderDetailsRepository;
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
    @Transactional
    public void deleteOrderById(long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found for id :: " + id);
        }
        logger.info("Deleting order with id {} and its related records", id);

        // Find Has records to get associated order detail IDs
        List<Has> hasRecords = hasRepository.findByOrderId(id);
        List<Long> orderDetailIds = hasRecords.stream().map(Has::getId).collect(Collectors.toList());

        // Delete Of and OrderDetails records linked via Has
        if (!orderDetailIds.isEmpty()) {
            ofRepository.deleteAllByIdIn(orderDetailIds);
            orderDetailsRepository.deleteAllByIdIn(orderDetailIds);
        }

        // Delete Has, Makes, and Pays records
        hasRepository.deleteByOrderId(id);
        makesRepository.deleteById(id);
        paysRepository.deleteById(new PaysKey(id));

        // Delete the Order itself
        orderRepository.deleteById(id);
        logger.info("Successfully deleted order with id {}", id);
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