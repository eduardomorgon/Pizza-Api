package br.com.github.eduardomorgon.pizza.service;

import br.com.github.eduardomorgon.pizza.exception.ResourceNotFoundException;
import br.com.github.eduardomorgon.pizza.model.Borda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.github.eduardomorgon.pizza.repository.BordaRepository;

/**
 *
 * @author eduardo
 */
@Service
public class BordaService {

    private final BordaRepository repository;

    @Autowired
    public BordaService(BordaRepository repository) {
        this.repository = repository;
    }

    public void delete(Borda tipoDeBorda) {
        repository.delete(tipoDeBorda);
    }

    public Borda find(Integer id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Borda save(Borda tipoDeBorda) {
        repository.save(tipoDeBorda);
        return tipoDeBorda;
    }

    public Iterable<Borda> all() {
        return repository.findAll();
    }

}
