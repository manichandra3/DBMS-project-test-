package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaysDTO {
    private PaysKey id;
    private Long customerId;
    private String customerName;
    private LocalDateTime paymentDate;
    private String paymentMode;
    private Long orderTotal;
}
