package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Has;
import com.thymeleafspringbootapplication.model.OrderDetails;
import com.thymeleafspringbootapplication.repository.HasRepository;
import com.thymeleafspringbootapplication.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HasServiceImpl implements HasService {

    private final HasRepository hasRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public HasServiceImpl(HasRepository hasRepository, OrderDetailsRepository orderDetailsRepository) {
        this.hasRepository = hasRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderDetails> getOrderDetails(Long orderId) {
        List<Has> hasList = hasRepository.findAll();
        List<OrderDetails> orderDetailsReturn = new ArrayList<>();
        List<Long> orderDidList = new ArrayList<>();
        for(Has has : hasList) {
            if(has.getOrderId().equals(orderId)) {
                orderDidList.add(has.getId());
            }
        }
        for(Long orderDid : orderDidList) {
            orderDetailsRepository.findById(orderDid)
                    .map(orderDetailsReturn::add);
        }
        return orderDetailsReturn;
    }

    @Override
    public void saveHas(Has has) {
        hasRepository.save(has);
    }
}
