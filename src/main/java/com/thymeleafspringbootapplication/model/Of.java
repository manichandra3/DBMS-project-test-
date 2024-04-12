package com.thymeleafspringbootapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`of`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Of {
    @Id
    @Column(name = "order_did")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

}
