package com.thymeleafspringbootapplication.service;

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
import java.util.Optional;

@Service
@AllArgsConstructor
public class NeedServiceImpl implements NeedService {

    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    NeedRepository needRepository;

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
        Optional<Product> optional = productRepository.findById(productId);
        Product product;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException(" Product not found for id :: " + productId);
        }
        return product;
    }

    @Override
    public Ingredient getIngredientById(Long ingredientId) {
        Optional<Ingredient> optional = ingredientRepository.findById(ingredientId);
        Ingredient ingredient;
        if (optional.isPresent()) {
            ingredient = optional.get();
        } else {
            throw new RuntimeException(" Ingredient not found for id :: " + ingredientId);
        }
        return ingredient;
    }
}
