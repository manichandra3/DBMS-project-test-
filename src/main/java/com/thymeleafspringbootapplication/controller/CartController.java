package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.*;
import com.thymeleafspringbootapplication.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CustomerServiceImpl customerServiceImpl;
    private final OrderServiceImpl orderServiceImpl;
    private final OrderDetailsServiceImpl orderDetailsServiceImpl;
    private final MakesServiceImpl makesServiceImpl;
    private final PaysServiceImpl paysServiceImpl;
    private final OfServiceImpl ofServiceImpl;
    private final HasServiceImpl hasServiceImpl;

    public CartController(CustomerServiceImpl customerServiceImpl, OrderServiceImpl orderServiceImpl, OrderDetailsServiceImpl orderDetailsServiceImpl, MakesServiceImpl makesServiceImpl, PaysServiceImpl paysServiceImpl, OfServiceImpl ofServiceImpl, HasServiceImpl hasServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
        this.orderServiceImpl = orderServiceImpl;
        this.orderDetailsServiceImpl = orderDetailsServiceImpl;
        this.makesServiceImpl = makesServiceImpl;
        this.paysServiceImpl = paysServiceImpl;
        this.ofServiceImpl = ofServiceImpl;
        this.hasServiceImpl = hasServiceImpl;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveCart(@RequestBody CartDTO cartDTO){
        Long cid = customerServiceImpl.getLastCustomerId();
        Long oid = orderServiceImpl.getLastOrderId();
        Long odid = orderDetailsServiceImpl.getLastOrderDid();

        Customer customer = new Customer();
        customer.setName(cartDTO.getCustomerName());
        customer.setContact(cartDTO.getCustomerContact());
        customerServiceImpl.saveCustomer(customer);

        Order order = new Order();
        order.setOrderDate(cartDTO.getOrderDate());
        order.setOrderTotal(cartDTO.getOrderTotal());
        orderServiceImpl.save(order);

        Makes makes = new Makes();
        makes.setId(oid+1);
        makes.setEmployeeId(cartDTO.getEmployeeId());
        makesServiceImpl.saveMakes(makes);

        Pays pays = new Pays();
        PaysKey paysKey = new PaysKey();
        paysKey.setOrderId(oid+1);
        pays.setId(paysKey);
        pays.setCustomerId(cid+1);
        pays.setPaymentMode(cartDTO.getPaymentMode());
        pays.setPaymentDate(cartDTO.getPaymentDate());
        paysServiceImpl.savePays(pays);

        List<OrderDetails> orderDetailsList = cartDTO.getOrderDetails();
        List<Long> productIdList = cartDTO.getProductIds();
        for (int i = 0; i< orderDetailsList.size(); i++) {
            OrderDetails orderDetails = (orderDetailsList.get(i));
            orderDetailsServiceImpl.saveOrderDetails(orderDetails);

            Has has = new Has();
            has.setId(odid+1);
            has.setOrderId(oid+1);
            hasServiceImpl.saveHas(has);

            Long productId = productIdList.get(i);
            Of of = new Of();
            of.setId(odid+1);
            of.setProductId(productId);
            ofServiceImpl.saveOf(of);
            odid++;
        }

        return ResponseEntity.ok().build();

    }

}
