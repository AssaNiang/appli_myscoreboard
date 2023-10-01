package com.example.appli.model;

import lombok.Data;

@Data
public class Jeu {
    private Long id;
    private String title;  
    private Integer maxPlayers;
    private Integer minPlayers;

    
}