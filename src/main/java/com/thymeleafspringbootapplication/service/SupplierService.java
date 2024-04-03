package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SupplierService {
    List<Supplier> getAllSuppliers();
    void saveSupplier(Supplier supplier);
    Supplier getSupplierById(long id);
    void deleteSupplierById(long id);
}
