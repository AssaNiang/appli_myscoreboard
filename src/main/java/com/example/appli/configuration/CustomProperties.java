package com.example.appli.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="com.example.appli")
// on definit le prefixe des propriétés du fichier application.properties
/*les propriétés de la classe auront le meme nom que les propriétés du fichier application.properties sans le préfixe */
public class CustomProperties {
    private String apiURL;
}