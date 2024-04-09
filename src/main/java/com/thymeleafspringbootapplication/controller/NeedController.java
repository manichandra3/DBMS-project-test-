package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.*;
import com.thymeleafspringbootapplication.service.NeedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/need")
public class NeedController {

    private final NeedService needService;
    public NeedController(NeedService needService) {
        this.needService = needService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<NeedDTO>> viewAllNeeds() {
        List<Need> needList = needService.getAllNeed();
        List<NeedDTO> needDTOList = ConvertToNeedDTO(needList);
        return ResponseEntity.ok(needDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveNeed(@RequestBody Need need) {
        try{
            needService.saveNeed(need);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{ingredientId}/{productId}")
    public ResponseEntity<Void> deleteNeed(@PathVariable Long ingredientId, @PathVariable Long productId) {
        try {
            needService.deleteNeedById(ingredientId, productId);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{ingredientId}/{productId}")
    public ResponseEntity<NeedDTO> viewNeed(@PathVariable Long ingredientId, @PathVariable Long productId) {
        try {
            Need need = needService.getNeedById(ingredientId, productId);
            NeedDTO needDTO = convertNeedToDTO(need);
            return ResponseEntity.ok(needDTO);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private List<NeedDTO> ConvertToNeedDTO(List<Need> needList) {
        return needList.stream()
                .map(this::convertNeedToDTO)
                .collect(Collectors.toList());
    }

    private NeedDTO convertNeedToDTO(Need need) {
        NeedDTO needDTO = new NeedDTO();
        needDTO.setId(need.getId());
        needDTO.setQuantity(need.getQuantity());

        Product product = need.getProduct();
        Ingredient ingredient = need.getIngredient();

        needDTO.setProductName(product.getName());
        needDTO.setIngredientName(ingredient.getIngredientName());
        return needDTO;
        
    }

}
