package com.example.appli.model;

import java.sql.Date;

import lombok.Data;
@Data
public class Partie {
    private Long id;
    private Date startDate;
    private String game;
    private String winner;
}