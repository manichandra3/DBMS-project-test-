package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Ingredient;
import com.thymeleafspringbootapplication.model.Supplier;
import com.thymeleafspringbootapplication.model.Supplies;
import com.thymeleafspringbootapplication.model.SuppliesKey;
import com.thymeleafspringbootapplication.repository.IngredientRepository;
import com.thymeleafspringbootapplication.repository.SupplierRepository;
import com.thymeleafspringbootapplication.repository.SuppliesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuppliesServiceImpl implements SuppliesService {

    private final SuppliesRepository suppliesRepository;
    private final SupplierRepository supplierRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
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
        SuppliesKey suppliesKey = new SuppliesKey(ingredientId,supplierId);
        Optional<Supplies> optional = suppliesRepository.findById(suppliesKey);
        Supplies supplies;
        if(optional.isPresent()){
            supplies = optional.get();
        } else {
            throw new RuntimeException("Supplier not found for id :: " + suppliesKey);
        }
        return supplies;
    }

    @Override
    public void deleteSupplyById(Long ingredientId, Long supplierId) {
        SuppliesKey suppliesKey = new SuppliesKey(ingredientId, supplierId);
        suppliesRepository.deleteById(suppliesKey);
    }

    @Override
    public Supplier getSupplierById(Long supplierId) {
        Optional<Supplier> optional = supplierRepository.findById(supplierId);
        Supplier supplier;
        if(optional.isPresent()) {
            supplier = optional.get();
        } else {
            throw new RuntimeException(" Supplier not found for id :: " + supplierId);
        }
        return supplier;
    }

    @Override
    public Ingredient getIngredientById(Long ingredientId) {
        Optional<Ingredient> optional = ingredientRepository.findById(ingredientId);
        Ingredient ingredient;
        if(optional.isPresent()) {
            ingredient = optional.get();
        } else {
            throw new RuntimeException(" Ingredient not found for id :: " + ingredientId);
        }
        return ingredient;
    }
}
