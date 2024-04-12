package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Customer;
import com.thymeleafspringbootapplication.service.CustomerService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@NoArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<Customer>> showAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        return ResponseEntity.ok(customerList);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveCustomer(@RequestBody Customer customer) {
        try {
            customerService.saveCustomer(customer);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

}
