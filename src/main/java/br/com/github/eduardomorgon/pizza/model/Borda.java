/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.github.eduardomorgon.pizza.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
//import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "BORDA")
public class Borda implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Nome é obrigatorio")
    @Size(min = 3, max = 25, message = "Nome precisa ter no minimo 3 caracteres.")
    private String nome;
    @NotEmpty(message = "Descrição é obrigatorio")
    private String descricao;

    public Borda() {
    }

    public Borda(String nome, String descricao) {
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

    @Override
    public String toString() {
        return "Borda{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Borda other = (Borda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
