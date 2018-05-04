/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.github.eduardomorgon.pizza.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author eduardo
 */
@Entity
@Getter
@AllArgsConstructor()
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Item implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ID_PIZZA")
    private Pizza pizza;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_DE_PIZZA")
    private TipoDePizza tipoDePizza;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ID_TIPO_DE_BORDA")
    private Borda tipoDeBorda;
    
    private String observacao;
    
    private int quantidade;
    
    public BigDecimal getValorItem() {
        
        return this.pizza.pegarValorPizza(tipoDePizza).multiply(BigDecimal.valueOf(this.quantidade));
    }
    
}
