package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Customer;
import com.thymeleafspringbootapplication.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

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
