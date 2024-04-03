package com.thymeleafspringbootapplication.controller;

import com.thymeleafspringbootapplication.model.Supplier;
import com.thymeleafspringbootapplication.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/showAll")
    public ResponseEntity<List<Supplier>> viewSuppliers() {
        List<Supplier> suppliersList =  supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliersList);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveSupplier(@RequestBody Supplier supplier) {
        supplierService.saveSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
    public ResponseEntity<Void> deleteSupplier(@PathVariable (value = "id") long id) {
        this.supplierService.deleteSupplierById(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/showFormForUpdate/{id}")
    public ResponseEntity<Supplier> showFormForSupplierUpdate(@PathVariable(value = "id") long id) {
        Supplier supplier = supplierService.getSupplierById(id);
        if (supplier != null) {
            return ResponseEntity.ok(supplier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
