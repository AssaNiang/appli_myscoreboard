package com.example.appli.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.appli.configuration.CustomProperties;
import com.example.appli.model.Joueur;

@Component
public class JoueurRepository extends Repository {
    // recupere lurl de l'api avec une injection de dependance
    @Autowired
    // CustomProperties properties;
    // String baseUrlApi;

    // en utilisant le constructeur le autowired ne sert a rien
    public JoueurRepository(CustomProperties customProperties) {
        // properties = customProperties;
        // baseUrlApi = properties.getApiURL();
        super(customProperties);
    }

    public Iterable<Joueur> getAllJoueurs() {
        // String baseUrlApi = properties.getApiURL();
        String getPlayerUrl = baseUrlApi + "/players";

        /*
         * L'obj de la class RestTemplate fait des requetes http et convertit le JSON
         * retourné par la requete en ob JAVA
         */
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Joueur>> response = restTemplate.exchange(
                getPlayerUrl,
                HttpMethod.GET,
                null,
                // type de la response ParameterizedTypeReference
                new ParameterizedTypeReference<Iterable<Joueur>>() {
                });
        return response.getBody();

    }

    public Joueur getJoueurById(long id) {
        // String baseUrlApi = properties.getApiURL();
        String getPlayerUrl = baseUrlApi + "/player/" + id;
        RestTemplate restTemplate = new RestTemplate();
        // retourn un joueur
        ResponseEntity<Joueur> response = restTemplate.exchange(
                // url appelé
                getPlayerUrl,
                HttpMethod.GET,
                null,
                // cmt on va le recevoir un obj de la class joueur
                Joueur.class

        );
        return response.getBody();

    }

    public Joueur addJoueur(Joueur e) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Joueur> request = new HttpEntity<Joueur>(e);
        ResponseEntity<Joueur> response = restTemplate.exchange(
                baseUrlApi + "/player",
                HttpMethod.POST,
                request,
                Joueur.class);
        return response.getBody();
    }

    public Boolean deleteJoueur(long id) {
        String getPlayerUrl = baseUrlApi + "/player/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(

                // url appelé
                getPlayerUrl,
                // dans quelle methode
                HttpMethod.DELETE,
                // les données envoyé
                null,
                // cmt on va le recevoir un obj de la class joueur
                Boolean.class

        );
        return response.getBody();
    }

    public Joueur updateJoueur(long id, Joueur e) {
        String getPlayerUrl = baseUrlApi + "/player/" + id;
        // si id n'est pas dans le paramettre on pouvait faire e.getId() à la place de +id
        RestTemplate restTemplate = new RestTemplate();
    //    renvoit tout un obj en creant le corps de la requette
        HttpEntity<Joueur> request = new HttpEntity<Joueur>(e);
        ResponseEntity<Joueur> response = restTemplate.exchange(
                // url appelé
                getPlayerUrl,
                HttpMethod.PUT,
                request,
                
                Joueur.class

        );
        return response.getBody();

    }
}