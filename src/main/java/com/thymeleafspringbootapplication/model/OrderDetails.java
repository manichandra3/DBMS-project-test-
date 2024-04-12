package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_DETAILS")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_did")
    private Long id;

    @Column(name = "product_quantity")
    private Long productQuantity;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_price")
    private Long orderPrice;

    public OrderDetails(Long productQuantity, String orderStatus, Long orderPrice) {
        this.productQuantity = productQuantity;
        this.orderStatus = orderStatus;
        this.orderPrice = orderPrice;
    }
}
