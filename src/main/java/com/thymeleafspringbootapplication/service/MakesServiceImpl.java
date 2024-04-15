package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Makes;
import com.thymeleafspringbootapplication.repository.MakesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MakesServiceImpl implements MakesService {

    private final MakesRepository makesRepository;
    @Autowired
    public MakesServiceImpl(MakesRepository repository) {
        this.makesRepository = repository;
    }

    @Override
    public List<Makes> findAll() {
        Iterable<Makes> iterable = makesRepository.findAll();
        List<Makes> makesList = new ArrayList<>();
        iterable.forEach(makesList::add);
        return makesList;
    }

    @Override
    public void saveMakes(Makes makes) {
        makesRepository.save(makes);
    }
}
