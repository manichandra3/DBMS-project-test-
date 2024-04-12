package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Has;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HasRepository extends JpaRepository<Has, Long> {
}
