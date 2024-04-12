package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.OrderDetails;
import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.model.SummaryDTO;
import com.thymeleafspringbootapplication.service.HasServiceImpl;
import com.thymeleafspringbootapplication.service.OfServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderSummary {
    private final HasServiceImpl hasServiceImpl;
    private final OfServiceImpl ofServiceImpl;

    public OrderSummary(HasServiceImpl hasServiceImpl, OfServiceImpl ofServiceImpl) {
        this.hasServiceImpl = hasServiceImpl;
        this.ofServiceImpl = ofServiceImpl;
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<SummaryDTO> orderSummary(@PathVariable("id") Long id) {
        SummaryDTO summaryDTO = new SummaryDTO();
        summaryDTO.setId(id);
        summaryDTO.setOrderDetailsList(hasServiceImpl.getOrderDetails(id));
        List<Product> productList = new ArrayList<>();
        for (OrderDetails orderDetails : summaryDTO.getOrderDetailsList()) {
            productList.add(ofServiceImpl.findProduct(orderDetails.getId()));
        }
        summaryDTO.setProductList(productList);
        return ResponseEntity.ok(summaryDTO);
    }
}
