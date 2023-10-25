package com.example.appli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.appli.model.Jeu;
import com.example.appli.model.Joueur;
import com.example.appli.service.JeuService;

@Controller
public class JeuController {
    
    @Autowired
    JeuService jeuService;

    @GetMapping("/jeux")
    public String getAllMyJeux(Model model) {
        Iterable<Jeu> jeux = jeuService.getJeux();
        model.addAttribute("jeux", jeux);
        return "jeux";
    }
    @GetMapping("/jeu/{id}/fiche")
    public String ficheDeJeu(@PathVariable("id") Long id, Model model) {

        Jeu j = jeuService.getJeu(id);
        model.addAttribute("jeu", j);
        return "jeu/fiche";
    }
     // pour afficher le formulaire
     @GetMapping("/jeu/ajouter")
     public String ajouter(Model model) {
         Jeu jeu = new Jeu();
         model.addAttribute("jeu", jeu);
         return "jeu/form";
     }

      // afficher le formulaire pour faire la modification
    @GetMapping("jeu/{id}/modifier")
    public String modifierUnJeu(@PathVariable("id") Long id, Model model){
        Jeu jeu = jeuService.getJeu(id);
        model.addAttribute("jeu",jeu);
        return "jeu/formUpdate";
    }
   // Pour valider la modification pour envoyer le form
    @PostMapping("/jeu/{id}/modifier")
    public ModelAndView ValidationUpdate(@ModelAttribute Jeu jeu) {
        jeuService.saveJeu(jeu);
        return new ModelAndView("redirect:/jeux");
    }
      // pour confirmer les données av de supprimer le jeu
      @GetMapping("jeu/{id}/supprimer")
      public String confirmer(@PathVariable("id") Long id, Model model) {
          Jeu jeu = jeuService.getJeu(id);
          model.addAttribute("jeu", jeu);
          return "jeu/ficheConfirm";
      }
  
      // supprimer
      @PostMapping("jeu/{id}/supprimer")
      public ModelAndView supprimer(@PathVariable("id") Long id) {
      jeuService.deleteJeu(id);
          return new ModelAndView("redirect:/jeux");
      }

 
}