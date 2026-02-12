package com.thymeleafspringbootapplication.controller;


import com.thymeleafspringbootapplication.model.Pays;
import com.thymeleafspringbootapplication.model.PaysDTO;
import com.thymeleafspringbootapplication.service.CustomerService;
import com.thymeleafspringbootapplication.service.OrderService;
import com.thymeleafspringbootapplication.service.PaysService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pays")
public class PaysController {
    private final PaysService paysService;
    private final CustomerService customerService;
    private final OrderService orderService;

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
            paysDTO.setCustomerName(customerService.getCustomerById(pays.getCustomerId()).getName());
            paysDTO.setCustomerId(pays.getCustomerId());
            paysDTO.setPaymentDate(orderService.getOrderById(pays.getId().getOrderId()).getOrderDate());
            paysDTO.setOrderTotal(orderService.getOrderById(pays.getId().getOrderId()).getOrderTotal());
            paysDTOList.add(paysDTO);
        }
        return paysDTOList;
    }

}
