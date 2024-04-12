package com.thymeleafspringbootapplication.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaysKey implements Serializable {

    @Column(name = "order_id")
    private Long orderId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaysKey paysKey = (PaysKey) o;
        return Objects.equals(orderId, paysKey.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderId);
    }
}
