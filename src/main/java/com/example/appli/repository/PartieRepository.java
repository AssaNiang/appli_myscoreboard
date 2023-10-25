package com.example.appli.repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.appli.configuration.CustomProperties;
import com.example.appli.model.Joueur;
import com.example.appli.model.Partie;

@Component
public class PartieRepository extends Repository {
    // constructeur
    public PartieRepository(CustomProperties customProperties) {
        super(customProperties);
    }

    public Iterable<Partie> getAllParties() {
        // String baseUrlApi = properties.getApiURL();
        String getPlayerUrl = baseUrlApi + "/contests";
        /*
         * L'obj de la class RestTemplate fait des requetes http
         * et convertit le JSON
         * retourné par la requete en ob JAVA
         */
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Partie>> response = restTemplate.exchange(
                getPlayerUrl,
                HttpMethod.GET,
                null,
                // type de la response ParameterizedTypeReference
                new ParameterizedTypeReference<Iterable<Partie>>() {
                });
        return response.getBody();
    }

    public Partie getPartieById(long id) {
        // String baseUrlApi = properties.getApiURL();
        String getPlayerUrl = baseUrlApi + "/contest/" + id;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Partie> response = restTemplate.exchange(
                // url appelé
                getPlayerUrl,
                HttpMethod.GET,
                null,
                // cmt on va le recevoir un obj de la class joueur
                Partie.class);
        return response.getBody();
    }

    public Partie addPartie(String start_date, Integer id_game) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // creer un object avec plusieurs type differents types de indice et le type des valeurs
        MultiValueMap<String, String>map =new LinkedMultiValueMap<String,String>();
        map.add("start_date",start_date);
        // rendre le id game en string
         map.add("id_game",id_game.toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<Partie> response = restTemplate.exchange(
                baseUrlApi + "/contest",
                HttpMethod.POST,
                request,
                Partie.class);
        return response.getBody();
    }

    public Boolean deletePartie(long id) {
        String getPlayerUrl = baseUrlApi + "/contest/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(
                // url appelé
                getPlayerUrl,
                // dans quelle methode
                HttpMethod.DELETE,
                // les données envoyé
                null,
                // cmt on va le recevoir un obj de la class joueur
                Boolean.class);
        return response.getBody();
    }

    public Partie updatePartie(long id, Partie e) {
        String getPlayerUrl = baseUrlApi + "/contest/" + id;

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Partie> request = new HttpEntity<Partie>(e);
        ResponseEntity<Partie> response = restTemplate.exchange(
                // url appelé
                getPlayerUrl,
                HttpMethod.PUT,
                request,

                Partie.class);
        return response.getBody();
    }
}