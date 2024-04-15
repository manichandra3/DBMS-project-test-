package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Has;
import com.thymeleafspringbootapplication.model.OrderDetails;

import java.util.List;

public interface HasService {
    List<OrderDetails> getOrderDetails(Long id);
    void saveHas(Has has);
}
