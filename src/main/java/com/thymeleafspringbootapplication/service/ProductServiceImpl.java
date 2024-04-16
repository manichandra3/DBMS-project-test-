package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{


    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        Iterable<Product> iterable = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        iterable.forEach(productList::add);
        return productList;
    }


    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product;
        if (optional.isPresent()) {
            product =  optional.get();
        } else {
            throw new RuntimeException(" Product not found for id :: " + id);
        }
        return product;
    }

    @Override
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        try {
            return productRepository.findAllByNameContainingIgnoreCase(name);
        } catch (Exception e) {
            throw new RuntimeException(" Product not found for name :: " + name);
        }
    }

    @Override
    public Double getTotalMakePrice(Long productId) {
        return productRepository.findProductMakePriceById(productId);
    }

    @Override
    public Double getTotalSellPrice(Long productId) {
        return productRepository.findProductSellPriceById(productId);
    }
}
