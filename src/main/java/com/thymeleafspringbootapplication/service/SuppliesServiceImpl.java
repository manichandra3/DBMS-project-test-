package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.*;
import com.thymeleafspringbootapplication.repository.IngredientRepository;
import com.thymeleafspringbootapplication.repository.SupplierRepository;
import com.thymeleafspringbootapplication.repository.SuppliesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuppliesServiceImpl implements SuppliesService {

    private final SuppliesRepository suppliesRepository;
    private final SupplierRepository supplierRepository;
    private final IngredientRepository ingredientRepository;

    public SuppliesServiceImpl(SuppliesRepository suppliesRepository, SupplierRepository supplierRepository, IngredientRepository ingredientRepository) {
        this.suppliesRepository = suppliesRepository;
        this.supplierRepository = supplierRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void saveSupply(Supplies supply) {
        suppliesRepository.save(supply);
    }

    @Override
    public List<Supplies> getAllSupplies() {
        return suppliesRepository.findAll();
    }

    @Override
    public Supplies getSupplyById(Long ingredientId, Long supplierId) {
        SuppliesKey suppliesKey = new SuppliesKey(ingredientId, supplierId);
        return suppliesRepository.findById(suppliesKey)
                .orElseThrow(() -> new ResourceNotFoundException("Supply not found for id :: " + suppliesKey));
    }

    @Override
    public void deleteSupplyById(Long ingredientId, Long supplierId) {
        SuppliesKey suppliesKey = new SuppliesKey(ingredientId, supplierId);
        suppliesRepository.deleteById(suppliesKey);
    }

    @Override
    public Supplier getSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found for id :: " + supplierId));
    }

    @Override
    public Ingredient getIngredientById(Long ingredientId) {
        return ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found for id :: " + ingredientId));
    }
}
