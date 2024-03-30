package com.thymeleafspringbootapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "exp_date")
    private Date expirationDate;

    @Column(name = "product_availability", nullable = false)
    private Long availability;

    @Column(name = "product_make_price", nullable = false)
    private Long makePrice;

    @Column(name = "product_sell_price", nullable = false)
    private Long sellPrice;

    // Constructors, getters, and setters
    public Product() {
    }

    public Product(String name, Date expirationDate, Long availability, Long makePrice, Long sellPrice) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.availability = availability;
        this.makePrice = makePrice;
        this.sellPrice = sellPrice;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getAvailability() {
        return availability;
    }

    public void setAvailability(Long availability) {
        this.availability = availability;
    }

    public Long getMakePrice() {
        return makePrice;
    }

    public void setMakePrice(Long makePrice) {
        this.makePrice = makePrice;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }
}