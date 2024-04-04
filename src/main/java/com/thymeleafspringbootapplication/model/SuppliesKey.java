package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuppliesKey implements Serializable {

    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Column(name = "supplier_id")
    private Long supplierId;

    // Constructors, getters, and setters
    // Make sure to override equals() and hashCode() methods
}

