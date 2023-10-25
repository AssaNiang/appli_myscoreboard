package com.example.appli.controller;

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
import com.example.appli.model.Partie;
import com.example.appli.service.JeuService;
import com.example.appli.service.PartieService;

@Controller
public class PartieController {
    @Autowired
    PartieService partieService;
    @Autowired
    JeuService jeuService;

    @GetMapping("parties")
    public String parties(Model model) {
        Iterable<Partie> parties = partieService.getParties();
        model.addAttribute("parties",
                parties);
        return "partie/partie";
    }

    @GetMapping("/partie/{id}/fiche")
    public String fiche(@PathVariable("id") Long id, Model model) {
        Partie p = partieService.getPartie(id);
        model.addAttribute("partie", p);
        return "partie/fiche";
    }

    // afficher le formulaire
    @GetMapping("/partie/ajouter")
    public String ajouter(Model model) {

        Iterable<Jeu> jeux = jeuService.getJeux();
        model.addAttribute("jeux", jeux);
        Partie p = new Partie();
        model.addAttribute("partie", p);
        return "partie/form";
    }

    // @PostMapping("/partie/ajouter")
    // public ModelAndView sauvegarder(@ModelAttribute Partie partie) {

        // partieService.savePartie(partie);
        // return new ModelAndView("redirect:/parties");
    // }
    @PostMapping("/partie/ajouter")
    public ModelAndView ajouter(@RequestParam String start_date,@RequestParam Integer id_game) {
         partieService.savePartie(start_date,id_game);
         return new ModelAndView("redirect:/parties");
    }

    @GetMapping("/partie/{id}/modifier")
    public String modifierUnePartie(@PathVariable("id") Long id,
            Model model) {
        Partie partie = partieService.getPartie(id);
        model.addAttribute("partie", partie);
        return "partie/form";

    }

    @PostMapping(value = "/partie/{id}/modifier")
    public ModelAndView ValidationUpdate(@PathVariable("id") Long id, @ModelAttribute Partie partie) {
        partie.setId(id);
        partieService.updatePartie(id, partie);
        return new ModelAndView("redirect:/parties");
    }

    @GetMapping("partie/{id}/supprimer")
    public String confirmer(@PathVariable("id") Long id, Model model) {
        Partie partie = partieService.getPartie(id);
        model.addAttribute("partie", partie);
        return "partie/ficheConfirm";
    }

    // supprimer
    @PostMapping("partie/{id}/supprimer")
    public ModelAndView supprimer(@PathVariable("id") Long id) {
        partieService.deletePartie(id);
        return new ModelAndView("redirect:/parties");
    }
}