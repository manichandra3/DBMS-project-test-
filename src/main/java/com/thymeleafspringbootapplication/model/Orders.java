package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "order_total")
    private Long orderTotal;

    public Orders(LocalDateTime orderDate, Long orderTotal) {
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }
}
