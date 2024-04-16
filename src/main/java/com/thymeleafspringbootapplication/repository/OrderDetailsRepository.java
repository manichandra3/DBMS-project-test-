package com.thymeleafspringbootapplication.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thymeleafspringbootapplication.model.OrderDetails;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long>{
    @Query(value = "SELECT order_details.order_did FROM ORDER_DETAILS ORDER BY ORDER_DETAILS.order_did desc limit 1", nativeQuery = true)
    Long findLastOrderDid();
    @Query(value = "SELECT SUM(ORDER_DETAILS.product_quantity) FROM ORDER_DETAILS NATURAL JOIN `of` WHERE `of`.product_id = :productId", nativeQuery = true)
    Long sumProductQuantityByProductId(@Param("productId") Long productId);
}
