package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
