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
    @Column(name = "customer_id")
    private Long customerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaysKey paysKey = (PaysKey) o;
        return Objects.equals(orderId, paysKey.orderId) && Objects.equals(customerId, paysKey.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId);
    }

}
