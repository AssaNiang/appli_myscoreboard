package com.example.appli.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.appli.configuration.CustomProperties;
import com.example.appli.model.Jeu;


import org.springframework.stereotype.Component;
@Component
public class JeuRepository {
    
     @Autowired
    CustomProperties properties;
    String baseUrlApi;
    
    public JeuRepository(CustomProperties customProperties){
        properties = customProperties;
        baseUrlApi = properties.getApiURL();
    }
      public Iterable<Jeu> getAllJeux() {
        // String baseUrlApi = properties.getApiURL();
        String getGameUrl = baseUrlApi + "/games";

        /*
         * L'obj de la class RestTemplate fait des requetes http et convertit le JSON
         * retourné par la requete en ob JAVA
         */
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Jeu>> response = restTemplate.exchange(
                getGameUrl,
                HttpMethod.GET,
                null,
                // type de la response ParameterizedTypeReference
                new ParameterizedTypeReference<Iterable<Jeu>>() {
                });
        return response.getBody();

    }
    public Jeu getJeuById(long id) {
        // String baseUrlApi = properties.getApiURL();
        String getGameUrl = baseUrlApi + "/game/" +id;
        RestTemplate restTemplate = new RestTemplate();
        // retourn un jeu
        ResponseEntity<Jeu> response = restTemplate.exchange(
            // url appelé
            getGameUrl,
            HttpMethod.GET,
            null,
            // cmt on va le recevoir un obj de la class joueur
             Jeu.class
   

        );
        return response.getBody();

     }
     public Jeu addJeu(Jeu e) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Jeu> request = new HttpEntity<Jeu>(e);
        ResponseEntity<Jeu> response = restTemplate.exchange(
            baseUrlApi + "/game",
            HttpMethod.POST,
            request,
            Jeu.class
        );
        return response.getBody();
    }
    public Boolean deleteJeu(long id) {
        String getGameUrl = baseUrlApi + "/game/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(

                // url appelé
                getGameUrl,
                // dans quelle methode
                HttpMethod.DELETE,
                // les données envoyé
                null,
                // cmt on va le recevoir un obj de la class joueur
                Boolean.class

        );
        return response.getBody();
    }
     public Jeu updateJeu(long id, Jeu e) {
        String getGameUrl = baseUrlApi + "/game/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Jeu> request = new HttpEntity<Jeu>(e);
        ResponseEntity<Jeu> response = restTemplate.exchange(
                // url appelé
                getGameUrl,
                HttpMethod.PUT,
                request,
                
                Jeu.class

        );
        return response.getBody();

    }
}