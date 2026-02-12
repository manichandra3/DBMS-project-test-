package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Supplier;
import com.thymeleafspringbootapplication.repository.SupplierRepository;
import com.thymeleafspringbootapplication.repository.SuppliesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService{

    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

    private final SupplierRepository supplierRepository;
    private final SuppliesRepository suppliesRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SuppliesRepository suppliesRepository) {
        this.supplierRepository = supplierRepository;
        this.suppliesRepository = suppliesRepository;
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
    @Transactional
    public void deleteSupplierById(long id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Supplier not found for id :: " + id);
        }
        logger.info("Deleting supplier with id {} and its related records", id);
        suppliesRepository.deleteBySupplierId(id);
        supplierRepository.deleteById(id);
        logger.info("Successfully deleted supplier with id {}", id);
    }
}
