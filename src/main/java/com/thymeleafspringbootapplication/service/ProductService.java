package com.thymeleafspringbootapplication.service;
import com.thymeleafspringbootapplication.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);
    Product getProductById(long id);
    void deleteProductById(long id);
    List<Product> getProductsByName(String Name);
    Double getTotalMakePrice(Long productId);
    Double getTotalSellPrice(Long productId);
}
