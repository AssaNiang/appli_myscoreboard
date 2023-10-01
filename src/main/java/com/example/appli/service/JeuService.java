package com.example.appli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appli.model.Jeu;
import com.example.appli.repository.JeuRepository;
import lombok.Data;

@Data
@Service
public class JeuService {
    
    @Autowired
    JeuRepository jeuRepository;
    public Iterable<Jeu>getJeux(){
        return jeuRepository.getAllJeux();
        
    }
    public Jeu getJeu(Long id){
        return jeuRepository.getJeuById(id);
    }
    public Jeu saveJeu(Jeu jeu) {
        return jeuRepository.addJeu(jeu);
    }
    public Boolean deleteJeu(Long id){
        return jeuRepository.deleteJeu(id);
    }
}