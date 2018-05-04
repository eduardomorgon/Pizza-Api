package br.com.github.eduardomorgon.pizza.service;

import br.com.github.eduardomorgon.pizza.exception.ResourceNotFoundException;
import br.com.github.eduardomorgon.pizza.model.Pedido;
import br.com.github.eduardomorgon.pizza.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo Morgon <eduardo.morgon at gmail.com>
 */
@Service
public class PedidoService {
    
    private final PedidoRepository repository;

    @Autowired
    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }
    
    public void delete(Integer id) {
        
        Pedido pedido = findById(id);
        repository.delete(pedido);
    }

    public Pedido findById(Integer id) {
        
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Pedido save(Pedido pedido) {
        
        return repository.save(pedido);
    }

    public Iterable<Pedido> all() {
        
        return repository.findAll();
    }

    
}
