package com.example.appli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.appli.model.Jeu;
import com.example.appli.service.JeuService;

@Controller
@RequestMapping("/recherche")
public class RechercheController {
    @Autowired
    JeuService jeuService;
    @GetMapping("jeu")
    // search = name de l'input word=la saisi dans le form
    /*Dans l'annotation requestParam le 1er argument est le nom de la valeur recuperer dans la requette http(par ex le 'name d'un input).
     * si la variableliée(ici,word) a le meme nom que la valeur recupérée (ici ,search), on n'a pas besoin de le préciser dans l'annotation 
     */
    public String rechercheJeu(@RequestParam("search") String word, Model model){
        Iterable<Jeu>jeux =jeuService.searchJeu(word);
        model.addAttribute("jeux",jeux); 
        model.addAttribute("word",word);

        return "recherche/jeu";
    }
}