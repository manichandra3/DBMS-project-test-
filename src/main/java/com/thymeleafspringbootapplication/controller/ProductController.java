package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/showAll")
    public ResponseEntity<List<Product>> viewProducts() {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
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


    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable (value = "id") long id) {
        this.productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/showFormForUpdate/{id}")
    public ResponseEntity<Product> showFormForProductUpdate(@PathVariable(value = "id") long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
