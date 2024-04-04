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
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "exp_date")
    private LocalDateTime expirationDate;

    @Column(name = "product_availability", nullable = false)
    private Long availability;

    @Column(name = "product_make_price", nullable = false)
    private Long makePrice;

    @Column(name = "product_sell_price", nullable = false)
    private Long sellPrice;


    public Product(String name, LocalDateTime expirationDate, Long availability, Long makePrice, Long sellPrice) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.availability = availability;
        this.makePrice = makePrice;
        this.sellPrice = sellPrice;
    }

}