package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Makes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakesRepository extends CrudRepository<Makes, Long> {
    void deleteByEmployeeId(Long employeeId);
}
