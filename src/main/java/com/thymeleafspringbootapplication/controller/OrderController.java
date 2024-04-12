package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Order;
import com.thymeleafspringbootapplication.model.TransactionDetails;
import com.thymeleafspringbootapplication.service.CustomerService;
import com.thymeleafspringbootapplication.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;

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

        return ResponseEntity.ok(order);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionDetails> showOrderByTransactionId(@PathVariable long id) {
        return null;
    }

}
