package com.thymeleafspringbootapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "has")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Has {
    @Id
    @Column(name = "order_did")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

}
