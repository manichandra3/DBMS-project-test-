package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetails {
    private long id;
    private String customer_name;
    private String payment_mode;
    private Long order_total;
}
