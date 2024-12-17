package com.xinchao.services;


import com.xinchao.models.Regulations;
import com.xinchao.repository.RegulationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegulationsService {

    @Autowired
    private RegulationsRepository regulationsRepository;

    public List<Regulations> getAllRegulations() {
        return regulationsRepository.findAll();
    }

    public Optional<Regulations> getRegulationById(String id) {
        return regulationsRepository.findById(id);
    }

    public Regulations createRegulation(Regulations regulations) {
        return regulationsRepository.save(regulations);
    }

    public Regulations updateRegulation(String id, Regulations regulations) {
        regulations.setId(id);
        return regulationsRepository.save(regulations);
    }

    public boolean deleteRegulation(String id) {
        if (regulationsRepository.existsById(id)) {
            regulationsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

