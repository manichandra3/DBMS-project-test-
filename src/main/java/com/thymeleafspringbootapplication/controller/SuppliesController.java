package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.*;
import com.thymeleafspringbootapplication.service.SuppliesService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/supplies")
public class SuppliesController {

    private final SuppliesService suppliesService;
    public SuppliesController(SuppliesService suppliesService) {
        this.suppliesService = suppliesService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<SuppliesDTO>> getAllSuppliesWithDetails() {
        try {
            List<Supplies> suppliesList = suppliesService.getAllSupplies();
            List<SuppliesDTO> suppliesDTOList = convertToSuppliesDTO(suppliesList);
            return new ResponseEntity<>(suppliesDTOList, HttpStatus.OK);
        } catch(DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveSupplies(@RequestBody Supplies supplies) {
        try {
            suppliesService.saveSupply(supplies);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete/{supplierId}/{ingredientId}")
    public ResponseEntity<Void> delete(@PathVariable long supplierId, @PathVariable long ingredientId) {
        suppliesService.deleteSupplyById(supplierId, ingredientId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{supplierId}/{ingredientId}")
    public ResponseEntity<SuppliesDTO> showFormForUpdate(@PathVariable long supplierId, @PathVariable long ingredientId) {
        // Call your service or repository layer to fetch the SuppliesDTO based on supplierId and ingredientId
        try{
            Supplies supplies = suppliesService.getSupplyById(supplierId, ingredientId);
            if (supplies != null) {
                // Other properties...
                SuppliesDTO suppliesDTO = convertSuppliesToDTO(supplies);
                return new ResponseEntity<>(suppliesDTO, HttpStatus.OK);
            } else {
                // Return ResponseEntity with not found status if SuppliesDTO is not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    private List<SuppliesDTO> convertToSuppliesDTO(List<Supplies> suppliesList) {
        return suppliesList.stream()
                .map(this::convertSuppliesToDTO)
                .collect(Collectors.toList());
    }

    private SuppliesDTO convertSuppliesToDTO(Supplies supplies) {
        SuppliesDTO suppliesDTO = new SuppliesDTO();
        suppliesDTO.setId(supplies.getId());
        suppliesDTO.setSupplyQuantity(supplies.getSupplyQuantity());
        suppliesDTO.setSupplyDate(supplies.getSupplyDate());
        suppliesDTO.setSupplyCost(supplies.getSupplyCost());
        suppliesDTO.setSupplyStatus(supplies.getSupplyStatus());

        Supplier supplier = supplies.getSupplier();
        Ingredient ingredient = supplies.getIngredient();

        suppliesDTO.setSupplierName(supplier.getSupplierName());
        suppliesDTO.setIngredientName(ingredient.getIngredientName());

        return suppliesDTO;
    }


}
