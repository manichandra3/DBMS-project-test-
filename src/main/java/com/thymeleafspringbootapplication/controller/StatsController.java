package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.service.OrderDetailsService;
import com.thymeleafspringbootapplication.service.OrderService;
import com.thymeleafspringbootapplication.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;
    private final ProductService productService;

    public StatsController(OrderService orderService, OrderDetailsService orderDetailsService, ProductService productService) {
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;
        this.productService = productService;
    }

    @GetMapping("/revenue")
    public ResponseEntity<Double> revenue() {
        double revenue = 0.0; // Accumulate revenue for all products
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            Long totalProductQuantity = orderDetailsService.getTotalOrderQuantity(product.getId());
            Double totalSellPrice = productService.getTotalSellPrice(product.getId());
            Double totalMakePrice = productService.getTotalMakePrice(product.getId());

            // Calculate revenue for the current product
            double productRevenue = totalProductQuantity * (totalSellPrice - totalMakePrice);
            revenue += productRevenue; // Accumulate revenue
        }
        return ResponseEntity.ok(revenue);
    }

    @GetMapping("/profit")
    public ResponseEntity<Long> profit() {
        List<Product> products = productService.getAllProducts();
        Long orderTotal = orderService.getOrderTotalSum();
        Double revenue = 0.0; // Accumulate revenue for all products

        for (Product product : products) {
            Long totalProductQuantity = orderDetailsService.getTotalOrderQuantity(product.getId());
            Double totalSellPrice = productService.getTotalSellPrice(product.getId());
            Double totalMakePrice = productService.getTotalMakePrice(product.getId());

            // Calculate revenue for the current product
            double productRevenue = totalProductQuantity * (totalSellPrice - totalMakePrice);
            revenue += productRevenue; // Accumulate revenue
        }

        // Calculate overall revenue by subtracting accumulated product revenue from order total
        Long overallRevenue = (long) (orderTotal - revenue);

        return ResponseEntity.ok(overallRevenue);
    }

}
