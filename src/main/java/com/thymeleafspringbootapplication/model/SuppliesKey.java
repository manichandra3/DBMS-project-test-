package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuppliesKey that = (SuppliesKey) o;
        return Objects.equals(ingredientId, that.ingredientId) &&
                Objects.equals(supplierId, that.supplierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, supplierId);
    }
}

