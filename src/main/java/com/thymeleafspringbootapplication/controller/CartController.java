package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.*;
import com.thymeleafspringbootapplication.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CustomerService customerService;
    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;
    private final MakesService makesService;
    private final PaysService paysService;
    private final OfService ofService;
    private final HasService hasService;

    public CartController(CustomerService customerService, OrderService orderService, OrderDetailsService orderDetailsService, MakesService makesService, PaysService paysService, OfService ofService, HasService hasService) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;
        this.makesService = makesService;
        this.paysService = paysService;
        this.ofService = ofService;
        this.hasService = hasService;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveCart(@RequestBody CartDTO cartDTO){
        Long cid = customerService.getLastCustomerId();
        Long oid = orderService.getLastOrderId();
        Long odid = orderDetailsService.getLastOrderDid();

        Customer customer = new Customer();
        customer.setName(cartDTO.getCustomerName());
        customer.setContact(cartDTO.getCustomerContact());
        customerService.saveCustomer(customer);

        Order order = new Order();
        order.setOrderDate(cartDTO.getOrderDate());
        order.setOrderTotal(cartDTO.getOrderTotal());
        orderService.save(order);

        Makes makes = new Makes();
        makes.setId(oid+1);
        makes.setEmployeeId(cartDTO.getEmployeeId());
        makesService.saveMakes(makes);

        Pays pays = new Pays();
        PaysKey paysKey = new PaysKey();
        paysKey.setOrderId(oid+1);
        pays.setId(paysKey);
        pays.setCustomerId(cid+1);
        pays.setPaymentMode(cartDTO.getPaymentMode());
        pays.setPaymentDate(cartDTO.getPaymentDate());
        paysService.savePays(pays);

        List<OrderDetails> orderDetailsList = cartDTO.getOrderDetails();
        List<Long> productIdList = cartDTO.getProductIds();
        for (int i = 0; i< orderDetailsList.size(); i++) {
            OrderDetails orderDetails = (orderDetailsList.get(i));
            orderDetailsService.saveOrderDetails(orderDetails);

            Has has = new Has();
            has.setId(odid+1);
            has.setOrderId(oid+1);
            hasService.saveHas(has);

            Long productId = productIdList.get(i);
            Of of = new Of();
            of.setId(odid+1);
            of.setProductId(productId);
            ofService.saveOf(of);
            odid++;
        }

        return ResponseEntity.ok().build();

    }

}
