package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuppliesDTO {
    private SuppliesKey id;
    private Long supplyQuantity;
    private Date supplyDate;
    private Long supplyCost;
    private String supplierName;
    private String ingredientName;
    private String supplyStatus;

}
