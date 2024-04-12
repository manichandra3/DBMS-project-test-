package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryDTO {
    private Long id;
    private List<OrderDetails> orderDetailsList;
    private List<Product> productList;
}
