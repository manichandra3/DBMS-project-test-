package com.thymeleafspringbootapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pays {

    @EmbeddedId
    private PaysKey id;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
}
