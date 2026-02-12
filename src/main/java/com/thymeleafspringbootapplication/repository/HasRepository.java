package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Has;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HasRepository extends JpaRepository<Has, Long> {
    List<Has> findByOrderId(Long orderId);
    void deleteByOrderId(Long orderId);
}
