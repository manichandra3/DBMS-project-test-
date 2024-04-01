package com.thymeleafspringbootapplication.model;

import javax.persistence.*;

@Entity
@Table(name = "INGREDIENT")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientId;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(name = "ingredient_price")
    private Long ingredientPrice;

    @Column(name = "Ingredient_quantity")
    private Float ingredientQuantity;

    @Column(name = "Ingredient_type")
    private String ingredientType;

    // Constructors, getters, and setters
    public Ingredient() {
    }
    public Ingredient(Long ingredientId, String ingredientName, Long ingredientPrice, Float ingredientQuantity, String ingredientType) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientType = ingredientType;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Long getIngredientPrice() {
        return ingredientPrice;
    }

    public void setIngredientPrice(Long ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public Float getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(Float ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }
}