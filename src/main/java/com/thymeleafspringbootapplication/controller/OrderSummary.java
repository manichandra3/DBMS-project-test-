package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.OrderDetails;
import com.thymeleafspringbootapplication.model.PaysKey;
import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.model.SummaryDTO;
import com.thymeleafspringbootapplication.service.CustomerService;
import com.thymeleafspringbootapplication.service.HasService;
import com.thymeleafspringbootapplication.service.OfService;
import com.thymeleafspringbootapplication.service.PaysService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderSummary {
    private final HasService hasService;
    private final OfService ofService;
    private final PaysService paysService;
    private final CustomerService customerService;

    public OrderSummary(HasService hasService, OfService ofService, PaysService paysService, CustomerService customerService) {
        this.hasService = hasService;
        this.ofService = ofService;
        this.paysService = paysService;
        this.customerService = customerService;
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<SummaryDTO> orderSummary(@PathVariable("id") Long id) {
        SummaryDTO summaryDTO = new SummaryDTO();
        summaryDTO.setId(id);
        summaryDTO.setOrderDetailsList(hasService.getOrderDetails(id));
        PaysKey paysKey = new PaysKey(id);
        summaryDTO.setCustomerName(customerService.getCustomerById(paysService.getPaysById(paysKey).getCustomerId()).getName());
        summaryDTO.setContact(customerService.getCustomerById(paysService.getPaysById(paysKey).getCustomerId()).getContact());
        summaryDTO.setPaymentMode(paysService.getPaysById(paysKey).getPaymentMode());
        List<Product> productList = new ArrayList<>();
        for (OrderDetails orderDetails : summaryDTO.getOrderDetailsList()) {
            productList.add(ofService.findProduct(orderDetails.getId()));
        }
        summaryDTO.setProductList(productList);
        return ResponseEntity.ok(summaryDTO);
    }
}
