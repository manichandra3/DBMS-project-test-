package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.service.EmployeeService;
import com.thymeleafspringbootapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/products/showAllProducts")
    public ResponseEntity<List<Product>> viewProducts() {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/employee/products/saveProduct")
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/employee/products/updateProduct/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product existingProduct = productService.getProductById(id);

        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }

        existingProduct.setName(productDetails.getName());
        existingProduct.setExpirationDate(productDetails.getExpirationDate());
        existingProduct.setAvailability(productDetails.getAvailability());
        existingProduct.setMakePrice(productDetails.getMakePrice());
        existingProduct.setSellPrice(productDetails.getSellPrice());

        productService.saveProduct(existingProduct);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/employee/products/deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable (value = "id") long id) {
        this.productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/employee/products/showFormForUpdate/{id}")
    public ResponseEntity<Product> showFormForUpdate(@PathVariable(value = "id") long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> showEmployeeProfile(@PathVariable(value = "id") long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
