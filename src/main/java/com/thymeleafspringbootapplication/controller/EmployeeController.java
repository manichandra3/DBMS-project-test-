package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Employee controller routes all the incoming HTTP requests to appropriate methods and pages.
@Controller
public class EmployeeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/employee/showAllProducts")
    public String viewProducts(Model model) {
        model.addAttribute("listProducts", productService.getAllProducts());
        return "show_products";
    }





}
