package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Pays;
import com.thymeleafspringbootapplication.model.PaysKey;
import org.springframework.data.repository.CrudRepository;

public interface PaysRepository extends CrudRepository<Pays, PaysKey> {

}
