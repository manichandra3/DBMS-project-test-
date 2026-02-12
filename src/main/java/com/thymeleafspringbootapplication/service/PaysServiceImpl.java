package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Pays;
import com.thymeleafspringbootapplication.model.PaysKey;
import com.thymeleafspringbootapplication.repository.PaysRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PaysServiceImpl implements PaysService{

    private final PaysRepository paysRepository;

    @Override
    public List<Pays> getAllPays() {
        List<Pays> pays = new ArrayList<>();
        Iterable<Pays> iterable = paysRepository.findAll();
        iterable.forEach(pays::add);
        return pays;
    }

    @Override
    public Pays getPaysById(PaysKey id) {
        return paysRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pays not found for id :: " + id));
    }

    @Override
    public void savePays(Pays pays) {
        paysRepository.save(pays);
    }

    @Override
    public void deletePays(PaysKey id) {
        paysRepository.deleteById(id);
    }
}
