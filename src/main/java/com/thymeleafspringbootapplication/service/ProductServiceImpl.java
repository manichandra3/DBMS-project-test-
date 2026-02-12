package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.repository.NeedRepository;
import com.thymeleafspringbootapplication.repository.OfRepository;
import com.thymeleafspringbootapplication.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final NeedRepository needRepository;
    private final OfRepository ofRepository;

    public ProductServiceImpl(ProductRepository productRepository, NeedRepository needRepository, OfRepository ofRepository) {
        this.productRepository = productRepository;
        this.needRepository = needRepository;
        this.ofRepository = ofRepository;
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
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for id :: " + id));
    }

    @Override
    @Transactional
    public void deleteProductById(long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found for id :: " + id);
        }
        logger.info("Deleting product with id {} and its related records", id);
        needRepository.deleteByProductId(id);
        ofRepository.deleteByProductId(id);
        productRepository.deleteById(id);
        logger.info("Successfully deleted product with id {}", id);
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
