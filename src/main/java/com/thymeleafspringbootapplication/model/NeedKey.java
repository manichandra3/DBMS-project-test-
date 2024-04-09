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
public class NeedKey implements Serializable {
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeedKey that = (NeedKey) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() { return Objects.hash(ingredientId, productId);}
}
