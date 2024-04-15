package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Makes;

import java.util.List;

public interface MakesService {

    List<Makes> findAll();

    void saveMakes(Makes makes);
}
