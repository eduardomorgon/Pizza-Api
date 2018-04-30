package br.com.github.eduardomorgon.pizza.service;

import br.com.github.eduardomorgon.pizza.exception.ResourceNotFoundException;
import br.com.github.eduardomorgon.pizza.model.Cliente;
import br.com.github.eduardomorgon.pizza.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo Morgon <eduardo.morgon at gmail.com>
 */
@Service
public class ClienteService {
    
    private final ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }
    
    public void delete(Integer id) {
        Cliente cliente = findById(id);
        repository.delete(cliente);
    }

    public Cliente findById(Integer id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Iterable<Cliente> all() {
        return repository.findAll();
    }

    public Page<Cliente> allPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
}
