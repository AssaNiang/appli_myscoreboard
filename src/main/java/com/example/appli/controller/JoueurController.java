package com.example.appli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.appli.model.Joueur;
import com.example.appli.service.JoueurService;

@Controller
public class JoueurController {
    @Autowired
    JoueurService joueurService;

    @GetMapping("joueurs")
    public String joueurs(Model model) {
        Iterable<Joueur> joueurs = joueurService.getJoueurs();
        model.addAttribute("joueurs", joueurs);
        return "home";
    }

    @GetMapping("/joueur/{id}/fiche")
    public String fiche(@PathVariable("id") Long id, Model model) {

        Joueur j = joueurService.getJoueur(id);
        model.addAttribute("joueur", j);
        return "joueur/fiche";
    }
    // pour afficher le formulaire
     @GetMapping("/joueur/ajouter")
    public String ajouter(Model model) {
        Joueur joueur = new Joueur();
        model.addAttribute("joueur", joueur);
        return "joueur/form";
    }
// Pour envoyer le formulaire
    @PostMapping("/joueur/ajouter")
    public ModelAndView sauvegarder(@ModelAttribute Joueur joueur){
        joueurService.saveJoueur(joueur);
        return new ModelAndView("redirect:/joueurs");
    }


}