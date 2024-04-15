package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Of;
import com.thymeleafspringbootapplication.model.Product;

public interface OfService {
    Product findProduct(Long id);
    void saveOf(Of of);
}
