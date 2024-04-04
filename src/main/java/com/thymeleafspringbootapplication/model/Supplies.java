package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Supplies")
public class Supplies {

    @EmbeddedId
    private SuppliesKey id;

    @Column(name = "supply_quantity", nullable = false)
    private Long supplyQuantity;

    @Column(name = "supply_date", nullable = false)
    private Date supplyDate;

    @Column(name = "supply_cost", nullable = false)
    private Long supplyCost;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", insertable = false, updatable = false)
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private Supplier supplier;

    // Constructors, getters, and setters
}
