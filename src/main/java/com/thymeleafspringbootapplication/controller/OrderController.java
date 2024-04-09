package com.thymeleafspringbootapplication.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.thymeleafspringbootapplication.service.OrderService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.thymeleafspringbootapplication.model.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    
    @GetMapping("/showAll")
    public ResponseEntity<List<Order>> showAllOrders() {
        List<Order> ordersList = orderService.getAllOrders();
        return ResponseEntity.ok(ordersList);
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
    public ResponseEntity<Order> showOrderById(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        if(order != null) {
            return ResponseEntity.ok(order);
        } else {
            throw new RuntimeException("Order not found for Id :: " + id);
        }
    }
    

}
