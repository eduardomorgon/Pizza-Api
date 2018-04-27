/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.github.eduardomorgon.pizza.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author eduardo
 */
public enum TipoDePizza {
    
    @JsonProperty("broto")BROTO, 
    @JsonProperty("media")MEDIA, 
    @JsonProperty("grande")GRANDE;
    
    
}
