package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Need;
import com.thymeleafspringbootapplication.model.NeedKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeedRepository extends JpaRepository<Need, NeedKey> {
}
