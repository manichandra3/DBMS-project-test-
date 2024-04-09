package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Supplies;
import com.thymeleafspringbootapplication.model.SuppliesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliesRepository extends JpaRepository<Supplies, SuppliesKey> {
    // You can define custom query methods here if needed
}
