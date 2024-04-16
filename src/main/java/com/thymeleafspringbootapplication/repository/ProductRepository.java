package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByNameContainingIgnoreCase(String name);
    @Query(value =  "SELECT SUM(PRODUCT.product_sell_price) FROM PRODUCT WHERE product_id = :productId", nativeQuery = true)
    Double findProductSellPriceById(Long productId);
    @Query(value =  "SELECT SUM(PRODUCT.product_make_price) FROM PRODUCT WHERE product_id = :productId", nativeQuery = true)
    Double findProductMakePriceById(Long productId);

}
