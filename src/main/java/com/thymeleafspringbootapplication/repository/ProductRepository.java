package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByNameContainingIgnoreCase(String name);
}
