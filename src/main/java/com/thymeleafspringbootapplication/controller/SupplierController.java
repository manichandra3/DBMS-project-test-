package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Supplier;
import com.thymeleafspringbootapplication.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<Supplier>> viewSuppliers() {
        try {
            List<Supplier> suppliersList = supplierService.getAllSuppliers();
            return ResponseEntity.ok(suppliersList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveSupplier(@RequestBody Supplier supplier) {
        try {
            supplierService.saveSupplier(supplier);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplierDetails) {
        Supplier existingSupplier = supplierService.getSupplierById(id);

        if (existingSupplier == null) {
            return ResponseEntity.notFound().build();
        }

        existingSupplier.setSupplierName(supplierDetails.getSupplierName());
        existingSupplier.setSupplierContact(supplierDetails.getSupplierContact());
        existingSupplier.setSupplyAddress(supplierDetails.getSupplyAddress());

        supplierService.saveSupplier(existingSupplier);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable(value = "id") long id) {
        try {
            this.supplierService.deleteSupplierById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> showFormForSupplierUpdate(@PathVariable(value = "id") long id) {
        try {
            Supplier supplier = supplierService.getSupplierById(id);
            if (supplier != null) {
                return ResponseEntity.ok(supplier);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
