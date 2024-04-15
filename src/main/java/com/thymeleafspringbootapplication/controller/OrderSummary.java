package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.OrderDetails;
import com.thymeleafspringbootapplication.model.PaysKey;
import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.model.SummaryDTO;
import com.thymeleafspringbootapplication.service.CustomerServiceImpl;
import com.thymeleafspringbootapplication.service.HasServiceImpl;
import com.thymeleafspringbootapplication.service.OfServiceImpl;
import com.thymeleafspringbootapplication.service.PaysServiceImpl;
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
    private final PaysServiceImpl paysServiceImpl;
    private final CustomerServiceImpl customerServiceImpl;

    public OrderSummary(HasServiceImpl hasServiceImpl, OfServiceImpl ofServiceImpl, PaysServiceImpl paysServiceImpl, CustomerServiceImpl customerServiceImpl) {
        this.hasServiceImpl = hasServiceImpl;
        this.ofServiceImpl = ofServiceImpl;
        this.paysServiceImpl = paysServiceImpl;
        this.customerServiceImpl = customerServiceImpl;
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<SummaryDTO> orderSummary(@PathVariable("id") Long id) {
        SummaryDTO summaryDTO = new SummaryDTO();
        summaryDTO.setId(id);
        summaryDTO.setOrderDetailsList(hasServiceImpl.getOrderDetails(id));
        PaysKey paysKey = new PaysKey(id);
        summaryDTO.setCustomerName(customerServiceImpl.getCustomerById(paysServiceImpl.getPaysById(paysKey).getCustomerId()).getName());
        summaryDTO.setContact(customerServiceImpl.getCustomerById(paysServiceImpl.getPaysById(paysKey).getCustomerId()).getContact());
        summaryDTO.setPaymentMode(paysServiceImpl.getPaysById(paysKey).getPaymentMode());
        List<Product> productList = new ArrayList<>();
        for (OrderDetails orderDetails : summaryDTO.getOrderDetailsList()) {
            productList.add(ofServiceImpl.findProduct(orderDetails.getId()));
        }
        summaryDTO.setProductList(productList);
        return ResponseEntity.ok(summaryDTO);
    }
}
