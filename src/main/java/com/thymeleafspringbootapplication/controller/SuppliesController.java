package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Ingredient;
import com.thymeleafspringbootapplication.model.Supplier;
import com.thymeleafspringbootapplication.model.Supplies;
import com.thymeleafspringbootapplication.model.SuppliesDTO;
import com.thymeleafspringbootapplication.service.SuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee/supplies")
public class SuppliesController {

    @Autowired
    private final SuppliesService suppliesService;

    public SuppliesController(SuppliesService suppliesService) {
        this.suppliesService = suppliesService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<SuppliesDTO>> getAllSuppliesWithDetails() {
        List<Supplies> suppliesList = suppliesService.getAllSupplies();
        List<SuppliesDTO> suppliesDTOList = convertToSuppliesDTO(suppliesList);
        return new ResponseEntity<>(suppliesDTOList, HttpStatus.OK);
    }

    private List<SuppliesDTO> convertToSuppliesDTO(List<Supplies> suppliesList) {
        List<SuppliesDTO> suppliesDTOList = new ArrayList<>();
        for (Supplies supply : suppliesList) {
            SuppliesDTO suppliesDTO = new SuppliesDTO();
            suppliesDTO.setId(supply.getId());
            suppliesDTO.setSupplyQuantity(supply.getSupplyQuantity());
            suppliesDTO.setSupplyDate(supply.getSupplyDate());
            suppliesDTO.setSupplyCost(supply.getSupplyCost());

            // Fetch supplier and ingredient details
            Supplier supplier = suppliesService.getSupplierById(supply.getId().getSupplierId());
            Ingredient ingredient = suppliesService.getIngredientById(supply.getId().getIngredientId());

            suppliesDTO.setSupplierName(supplier.getSupplierName());
            suppliesDTO.setIngredientName(ingredient.getIngredientName());

            suppliesDTOList.add(suppliesDTO);
        }
        return suppliesDTOList;
    }
}
