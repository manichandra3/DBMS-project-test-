package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Ingredient;
import com.thymeleafspringbootapplication.model.Need;
import com.thymeleafspringbootapplication.model.NeedKey;
import com.thymeleafspringbootapplication.model.Product;
import com.thymeleafspringbootapplication.repository.IngredientRepository;
import com.thymeleafspringbootapplication.repository.NeedRepository;
import com.thymeleafspringbootapplication.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NeedServiceImpl implements NeedService {

    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    private final NeedRepository needRepository;

    @Override
    public void saveNeed(Need need) {
        needRepository.save(need);
    }

    @Override
    public List<Need> getAllNeed() {
        return needRepository.findAll();
    }

    @Override
    public Need getNeedById(Long ingredientId, Long productId) {
        NeedKey needKey = new NeedKey(ingredientId, productId);
        return needRepository.getReferenceById(needKey);
    }

    @Override
    public void deleteNeedById(Long ingredientId, Long supplierId) {
        NeedKey needKey = new NeedKey(ingredientId, supplierId);
        needRepository.deleteById(needKey);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for id :: " + productId));
    }

    @Override
    public Ingredient getIngredientById(Long ingredientId) {
        return ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found for id :: " + ingredientId));
    }
}
