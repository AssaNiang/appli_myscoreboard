package com.example.appli.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.appli.configuration.CustomProperties;

public class Repository {
 
    CustomProperties properties;
    String baseUrlApi;

 public Repository(CustomProperties customProperties) {
     properties = customProperties;
     baseUrlApi = properties.getApiURL();
 }
 public Repository(){}
}