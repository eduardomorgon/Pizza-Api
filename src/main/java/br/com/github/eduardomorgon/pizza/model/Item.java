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

/**
 *
 * @author eduardo
 */
@Entity
public class Item implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PIZZA")
    private Pizza pizza;
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_DE_PIZZA")
    private TipoDePizza tipoDePizza;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TIPO_DE_BORDA")
    private Borda tipoDeBorda;
    private String observacao;
    private int quantidade;

    public Item() {
    }
    
    public Item(Pizza pizza, TipoDePizza tipoDePizza, Borda tipoDeBorda, String observacao, int quantidade) {
        this.pizza = pizza;
        this.tipoDePizza = tipoDePizza;
        this.tipoDeBorda = tipoDeBorda;
        this.observacao = observacao;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }
    
    public Pizza getPizza() {
        return pizza;
    }

    public TipoDePizza getTipoDePizza() {
        return tipoDePizza;
    }
    
    public Borda getTipoDeBorda() {
        return tipoDeBorda;
    }

    public String getObservacao() {
        return observacao;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    public BigDecimal getValorItem() {
        return this.pizza.pegarValorPizza(tipoDePizza).multiply(BigDecimal.valueOf(this.quantidade));
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", pizza=" + pizza.getNome() + ", tipoDePizza=" + tipoDePizza + ", tipoDeBorda="+tipoDeBorda.getNome()+", observacao=" + observacao + ", quantidade=" + quantidade + ", valor="+this.getValorItem()+" }";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
