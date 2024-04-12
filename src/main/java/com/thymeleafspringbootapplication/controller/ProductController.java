package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<Product>> viewProducts() {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {
        try {
            productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product existingProduct = productService.getProductById(id);

        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            existingProduct.setName(productDetails.getName());
            existingProduct.setExpirationDate(productDetails.getExpirationDate());
            existingProduct.setAvailability(productDetails.getAvailability());
            existingProduct.setMakePrice(productDetails.getMakePrice());
            existingProduct.setSellPrice(productDetails.getSellPrice());

            productService.saveProduct(existingProduct);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable (value = "id") long id) {
        this.productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> showFormForProductUpdate(@PathVariable(value = "id") long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByName/{name}")
    public ResponseEntity<List<Product>> searchProductByName(@PathVariable(value = "name") String name) {
        try {
            List<Product> productList = productService.getProductsByName(name);
            return ResponseEntity.ok(productList);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
