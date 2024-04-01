package com.thymeleafspringbootapplication.model;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDid;

    @Column(name = "product_quantity")
    private Long productQuantity;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_price")
    private Long orderPrice;

    // Constructors, getters, and setters


    public OrderDetails() {
    }

    public OrderDetails(Long orderDid, Long productQuantity, String orderStatus, Long orderPrice) {
        this.orderDid = orderDid;
        this.productQuantity = productQuantity;
        this.orderStatus = orderStatus;
        this.orderPrice = orderPrice;
    }

    public Long getOrderDid() {
        return orderDid;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderDid(Long orderDid) {
        this.orderDid = orderDid;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }
}
