package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Supplies;
import com.thymeleafspringbootapplication.model.SuppliesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliesRepository extends JpaRepository<Supplies, SuppliesKey> {
    @Modifying
    @Query("DELETE FROM Supplies s WHERE s.id.ingredientId = :ingredientId")
    void deleteByIngredientId(@Param("ingredientId") Long ingredientId);

    @Modifying
    @Query("DELETE FROM Supplies s WHERE s.id.supplierId = :supplierId")
    void deleteBySupplierId(@Param("supplierId") Long supplierId);
}
