package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Supplier;
import com.thymeleafspringbootapplication.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService{

    private final SupplierRepository supplierRepository;

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
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found for id :: " + id));
    }

    @Override
    public void deleteSupplierById(long id) {
        supplierRepository.deleteById(id);
    }
}
