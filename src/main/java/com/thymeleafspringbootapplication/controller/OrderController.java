package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Order;
import com.thymeleafspringbootapplication.model.OrderDTO;
import com.thymeleafspringbootapplication.model.PaysKey;
import com.thymeleafspringbootapplication.service.OrderService;
import com.thymeleafspringbootapplication.service.PaysServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final PaysServiceImpl paysServiceImpl;

    @GetMapping("/showAll")
    public ResponseEntity<List<OrderDTO>> showAllOrders() {
        List<Order> ordersList = orderService.getAllOrders();
        List<OrderDTO> orderDTOList = convertToOrderDTO(ordersList);
        return ResponseEntity.ok(orderDTOList);
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

    @GetMapping("/details/showAll")
    public ResponseEntity<List<OrderDTO>> showAllOrdersDetails() {
        List<Order> ordersList = orderService.getAllOrders();
        List<OrderDTO> orderDTOList = convertToOrderDTO(ordersList);
        return ResponseEntity.ok(orderDTOList);
    }

    public List<OrderDTO> convertToOrderDTO(List<Order> orders) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {
            PaysKey paysKey = new PaysKey(order.getOrderId());
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderTotal(order.getOrderTotal());
            orderDTO.setOrderDate(order.getOrderDate());
            orderDTO.setId(order.getOrderId());
            orderDTO.setCustomerId(paysServiceImpl.getPaysById(paysKey).getCustomerId());
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

}
