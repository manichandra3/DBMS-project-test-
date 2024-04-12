package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaysDTO {
    private PaysKey id;
    private String customer_name;
    private String payment_mode;
    private Long order_total;
}
