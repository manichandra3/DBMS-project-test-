package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Of;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfRepository extends CrudRepository<Of, Long> {
    void deleteByProductId(Long productId);

    @Modifying
    @Query("DELETE FROM Of o WHERE o.id IN :ids")
    void deleteAllByIdIn(@Param("ids") List<Long> ids);
}
