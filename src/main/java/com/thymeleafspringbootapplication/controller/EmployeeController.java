package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.service.EmployeeService;
import com.thymeleafspringbootapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// Employee controller routes all the incoming HTTP requests to appropriate methods and pages.
// Employee manages products.
@Controller
public class EmployeeController {

    @Autowired
    private ProductService productService;
    @Autowired
    private EmployeeService employeeService;

//    Display the details of all the products in the DB.
    @GetMapping("/employee/products/showAllProducts")
    public String viewProducts(Model model) {
        model.addAttribute("listProducts", productService.getAllProducts());
        return "show_products";
    }

    @PostMapping("/employee/products/showAllProducts")
    public String employeeLogin(@RequestParam Long employeeId, Model model) {
        model.addAttribute("employeeId",employeeId);
        return "show_employee_profile";
    }


//    Add a new product.
    @PostMapping("/employee/products/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/employee/products/showProducts";
    }

//    Display the product details form for update.
    @GetMapping("/employee/products/showFormForUpdate/{id}")
    public String showFromForUpdate(@PathVariable(value="id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "update_product";
    }

//    Display the employee profile.
    @GetMapping("/employee/profile/{id}")
    public String showEmployeeProfile(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "show_employee_profile";
    }

}
