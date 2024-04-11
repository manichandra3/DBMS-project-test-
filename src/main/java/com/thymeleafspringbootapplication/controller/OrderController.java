package com.thymeleafspringbootapplication.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thymeleafspringbootapplication.service.CustomerService;
import com.thymeleafspringbootapplication.service.OrderService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.thymeleafspringbootapplication.model.Customer;
import com.thymeleafspringbootapplication.model.Order;
import com.thymeleafspringbootapplication.model.OrderDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    
    @GetMapping("/showAll")
    public ResponseEntity<List<OrderDTO>> showAllOrders() {
        List<Order> ordersList = orderService.getAllOrders();
        List<OrderDTO> orderDTOs = convertToOrdersDTO(ordersList);
        return ResponseEntity.ok(orderDTOs);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveOrder(@RequestBody Order order) {
        orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> showOrderById(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        OrderDTO orderDTO = convertOrdersToDTO(order);
        if(order != null) {
            return ResponseEntity.ok(orderDTO);
        } else {
            throw new RuntimeException("Order not found for Id :: " + id);
        }
    }

    private List<OrderDTO> convertToOrdersDTO(List<Order> orderList) {
        return orderList.stream()
                .map(this::convertOrdersToDTO)
                .collect(Collectors.toList());
    } 

    private OrderDTO convertOrdersToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getOrderId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderTotal(order.getOrderTotal());
        try {
            Customer customer = customerService.getCustomerById(order.getCustomerId());
            orderDTO.setCustomerName(customer.getName());
        } catch(RuntimeException e) {
            throw new RuntimeException("Customer not found for Id :: " + order.getCustomerId());
        }
        return orderDTO;
    }
    

}
