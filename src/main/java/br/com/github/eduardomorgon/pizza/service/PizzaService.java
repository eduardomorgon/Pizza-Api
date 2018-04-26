package br.com.github.eduardomorgon.pizza.service;

import br.com.github.eduardomorgon.pizza.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.github.eduardomorgon.pizza.model.Pizza;
import br.com.github.eduardomorgon.pizza.repository.PizzaRepository;

/**
 *
 * @author eduardo
 */
@Service
public class PizzaService {

    private final PizzaRepository repository;

    @Autowired
    public PizzaService(PizzaRepository repository) {
        this.repository = repository;
    }

    public void delete(Integer id) {
        Pizza pizza = findById(id);
        repository.delete(pizza);
    }

    public Pizza findById(Integer id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Pizza save(Pizza pizza) {
        return repository.save(pizza);
    }

    public Iterable<Pizza> all() {
        return repository.findAll();
    }

    public Page<Pizza> allPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
