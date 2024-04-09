package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
