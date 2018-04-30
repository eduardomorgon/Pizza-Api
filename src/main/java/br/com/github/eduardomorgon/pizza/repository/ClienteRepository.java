package br.com.github.eduardomorgon.pizza.repository;

import br.com.github.eduardomorgon.pizza.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Morgon <eduardo.morgon at gmail.com>
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
