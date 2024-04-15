package com.thymeleafspringbootapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "makes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Makes {
    @Id
    @Column(name = "order_id")
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

}
