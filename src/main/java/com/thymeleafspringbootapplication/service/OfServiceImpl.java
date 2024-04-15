package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Of;
import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.repository.OfRepository;
import com.thymeleafspringbootapplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfServiceImpl implements OfService {

    private final ProductRepository productRepository;
    private final OfRepository ofRepository;
    @Autowired
    public OfServiceImpl(ProductRepository productRepository, OfRepository ofRepository) {
        this.productRepository = productRepository;
        this.ofRepository = ofRepository;
    }

    @Override
    public Product findProduct(Long orderDid) {
        Optional<Of> optional = ofRepository.findById(orderDid);
        if (optional.isPresent()) {
            Of of = optional.get();
            Optional<Product> optionalProduct = productRepository.findById(of.getProductId());
            if (optionalProduct.isPresent()) {
                return optionalProduct.get();
            }
        }
        return null;
    }

    @Override
    public void saveOf(Of of) {
        ofRepository.save(of);
    }
}
