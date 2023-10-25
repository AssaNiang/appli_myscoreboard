package com.example.appli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appli.model.Partie;
import com.example.appli.repository.PartieRepository;

import lombok.Data;

@Data
@Service
public class PartieService {
    @Autowired
    PartieRepository partieRepository;

    public Iterable<Partie> getParties() {
        return partieRepository.getAllParties();
    }

    public Partie getPartie(Long id) {
        return partieRepository.getPartieById(id);
    }

    public Partie savePartie(String start_date, Integer game_id) {
        return partieRepository.addPartie(start_date,game_id);       

    }

    public Boolean deletePartie(Long id) {
       
        return partieRepository.deletePartie(id);        

    }

    public Partie updatePartie(Long id, Partie partie) {
        return partieRepository.updatePartie(id,partie);

    }
}