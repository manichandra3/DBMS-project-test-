package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Ingredient;
import com.thymeleafspringbootapplication.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<Ingredient>> viewAllIngredients() {
        List<Ingredient> ingredientList = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredientList);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveIngredient(@RequestBody Ingredient ingredient) {
        try {
            ingredientService.saveIngredient(ingredient);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            throw new RuntimeException("Couldn't save Ingredient!");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateIngredient(@PathVariable long id, @RequestBody Ingredient updatedIngredient) {
        Ingredient ingredient = ingredientService.getIngredientById(id);

        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the ingredient properties with the new values
        ingredient.setIngredientName(updatedIngredient.getIngredientName());
        ingredient.setIngredientPrice(updatedIngredient.getIngredientPrice());
        ingredient.setIngredientQuantity(updatedIngredient.getIngredientQuantity());
        ingredient.setIngredientType(updatedIngredient.getIngredientType());

        // Save the updated ingredient
        ingredientService.saveIngredient(ingredient);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable long id) {
        ingredientService.deleteIngredientById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> showFromForIngredientUpdate(@PathVariable long id) {
        try {
            Ingredient ingredient = ingredientService.getIngredientById(id);
            if (ingredient != null) {
                return ResponseEntity.ok(ingredient);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
