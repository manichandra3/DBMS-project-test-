package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public Ingredient(String ingredientName, Long ingredientPrice, Float ingredientQuantity, String ingredientType) {
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientType = ingredientType;
    }
}