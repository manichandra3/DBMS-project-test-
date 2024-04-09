package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Supplier;
import com.thymeleafspringbootapplication.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService{

    SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return (List<Supplier>) supplierRepository.findAll();
    }

    @Override
    public void saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public Supplier getSupplierById(long id) {
        Optional<Supplier> optional = supplierRepository.findById(id);
        Supplier supplier;
        if (optional.isPresent()) {
            supplier =  optional.get();
        } else {
            throw new RuntimeException("Supplier not found for id :: " + id);
        }
        return supplier;
    }

    @Override
    public void deleteSupplierById(long id) {
        supplierRepository.deleteById(id);
    }
}
