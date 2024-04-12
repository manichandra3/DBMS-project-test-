package com.thymeleafspringbootapplication.controller;


import com.thymeleafspringbootapplication.model.Pays;
import com.thymeleafspringbootapplication.model.PaysDTO;
import com.thymeleafspringbootapplication.service.CustomerService;
import com.thymeleafspringbootapplication.service.OrderService;
import com.thymeleafspringbootapplication.service.PaysService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pays")

@NoArgsConstructor
public class PaysController {
    private PaysService paysService;
    private CustomerService customerService;
    private OrderService orderService;

    @Autowired
    public PaysController(PaysService paysService, CustomerService customerService, OrderService orderService) {
        this.paysService = paysService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<Pays>> getAllPays() {
        return ResponseEntity.ok(paysService.getAllPays());
    }

    @GetMapping("/transactions/showAll")
    public ResponseEntity<List<PaysDTO>> getAllTransactions() {
        List<Pays> paysList = this.paysService.getAllPays();
        List<PaysDTO> paysDTOList = ConvertToPaysDTO(paysList);
        return ResponseEntity.ok(paysDTOList);
    }

    public List<PaysDTO> ConvertToPaysDTO(List<Pays> paysList) {
        List<PaysDTO> paysDTOList = new ArrayList<>();
        for (Pays pays : paysList) {
            PaysDTO paysDTO = new PaysDTO();
            paysDTO.setId(pays.getId());
            paysDTO.setCustomer_name(customerService.getCustomerById(pays.getId().getCustomerId()).getName());
            paysDTO.setOrder_total(orderService.getOrderById(pays.getId().getOrderId()).getOrderTotal());
            paysDTOList.add(paysDTO);
        }
        return paysDTOList;
    }

}
