package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class CartDTO {
    private final String customerName;
    private final String customerContact;
    private final LocalDateTime orderDate;
    private final Long orderTotal;

    private final String paymentMode;
    private final LocalDateTime paymentDate;

    private final Long employeeId;

    private final List<Long> productIds;

    private final List<OrderDetails> orderDetails;

}
