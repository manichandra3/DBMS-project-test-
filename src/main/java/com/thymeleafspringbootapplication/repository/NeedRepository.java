package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Need;
import com.thymeleafspringbootapplication.model.NeedKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NeedRepository extends JpaRepository<Need, NeedKey> {
    @Modifying
    @Query("DELETE FROM Need n WHERE n.id.productId = :productId")
    void deleteByProductId(@Param("productId") Long productId);

    @Modifying
    @Query("DELETE FROM Need n WHERE n.id.ingredientId = :ingredientId")
    void deleteByIngredientId(@Param("ingredientId") Long ingredientId);
}
