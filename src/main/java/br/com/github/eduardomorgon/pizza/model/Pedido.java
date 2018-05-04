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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author eduardo
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ID_CLIENTE")
    @Setter
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PEDIDO_ID")
    private List<Item> itens = new ArrayList<>();
    
    public BigDecimal getValorTotal() {
        
        return itens.stream().map(item -> item.getValorItem()).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
    
    public int getQuantidadeDePizza() {
        
        return this.itens.stream().mapToInt(item -> item.getQuantidade()).sum();
    }
         
}
