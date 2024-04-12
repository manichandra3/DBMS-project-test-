package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Pays;
import com.thymeleafspringbootapplication.model.PaysKey;

import java.util.List;

public interface PaysService {

    List<Pays> getAllPays();

    Pays getPaysById(PaysKey id);

    void savePays(Pays pays);

    void deletePays(PaysKey id);
}
