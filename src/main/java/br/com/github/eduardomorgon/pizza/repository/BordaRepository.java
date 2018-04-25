/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.github.eduardomorgon.pizza.repository;

import br.com.github.eduardomorgon.pizza.model.Borda;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eduardo
 */
@Repository
public interface BordaRepository extends CrudRepository<Borda, Integer> {
    
}
