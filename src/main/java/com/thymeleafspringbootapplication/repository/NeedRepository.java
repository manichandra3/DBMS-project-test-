package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Need;
import com.thymeleafspringbootapplication.model.NeedKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeedRepository extends JpaRepository<Need, NeedKey> {
}
