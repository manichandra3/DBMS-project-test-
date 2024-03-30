package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// Employee controller routes all the incoming HTTP requests to appropriate methods and pages.
// Employee manages products.
@Controller
public class EmployeeController {

    @Autowired
    private ProductService productService;

//    Display the details of all the products in the DB.
    @GetMapping("/employee/products/showAllProducts")
    public String viewProducts(Model model) {
        model.addAttribute("listProducts", productService.getAllProducts());
        return "show_products";
    }

//    Add a new product.
    @PostMapping("/employee/products/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/employee/products/showProducts";
    }

//    Display form for product details update.
    @GetMapping("/employee/products/showFormForUpdate/{id}")
    public String showFromForUpdate(@PathVariable(value="id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "update_product";
    }



}
