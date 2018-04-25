package br.com.github.eduardomorgon.pizza.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.github.eduardomorgon.pizza.model.Pizza;

@Repository
public interface PizzaRepository extends PagingAndSortingRepository<Pizza, Integer> {

}