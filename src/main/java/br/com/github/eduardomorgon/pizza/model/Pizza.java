/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.github.eduardomorgon.pizza.model;

import static br.com.github.eduardomorgon.pizza.model.TipoDePizza.*;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;

/**
 *
 * @author eduardo
 */
@Entity
public class Pizza implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    @ElementCollection
    @CollectionTable(name="PRECOS_PIZZA", joinColumns = @JoinColumn(name = "PIZZA_ID"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "TIPO_DE_PIZZA")
    @Column(name="PRECO")
    private Map<TipoDePizza, BigDecimal> precos;

    public Pizza() {
        this.precos = new HashMap<>();
    }

    public Pizza(String nome, String descricao) {
        this();
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Map<TipoDePizza, BigDecimal> getPrecos() {
        return precos;
    }

    public void criarPrecos(BigDecimal broto, BigDecimal media, BigDecimal grande) {
        this.precos.put(BROTO, broto);
        this.precos.put(MEDIA, media);
        this.precos.put(GRANDE, grande);
    }
    
    public BigDecimal pegarValorPizza(TipoDePizza tipo) {
        return this.precos.get(tipo);
    }

    @Override
    public String toString() {
        return "Pizza{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", precos=" + precos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Pizza other = (Pizza) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
