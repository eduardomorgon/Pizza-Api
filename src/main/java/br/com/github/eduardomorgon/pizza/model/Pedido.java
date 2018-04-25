/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.github.eduardomorgon.pizza.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author eduardo
 */
@Entity
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ENDERECO")
    private Endereco endereco;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID")
    private List<Item> itens;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public Pedido(Cliente cliente, Endereco endereco, List<Item> itens) {
        this.cliente = cliente;
        this.endereco = endereco;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Item> getItens() {
        return itens;
    }
    
    public  BigDecimal getValorTotal() {
        return itens.stream().map(i -> i.getValorItem()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
    
    public int getQuantidadeDePizza() {
        return this.itens.stream().mapToInt(i -> i.getQuantidade()).sum();
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", cliente=" + cliente.getNome() + ", endereco=" + endereco + ", itens=" + itens + ", total="+this.getValorTotal()+"}";
    }

         
}
