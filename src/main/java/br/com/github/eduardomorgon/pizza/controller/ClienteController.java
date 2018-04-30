package br.com.github.eduardomorgon.pizza.controller;

import br.com.github.eduardomorgon.pizza.model.Cliente;
import br.com.github.eduardomorgon.pizza.service.ClienteService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Eduardo Morgon <eduardo.morgon at gmail.com>
 */
@RestController
@RequestMapping("clientes")
public class ClienteController {
    
    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Iterable<Cliente> all() {
        
        return service.all();
    }
    
    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Cliente find(@PathVariable Integer id) {
        
        return service.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Cliente cliente, HttpServletRequest request, HttpServletResponse response) {
        
        Cliente clienteCreated = service.save(cliente);
        response.setHeader("Location", request.getRequestURL().append(clienteCreated.getId()).toString());
    }
    
    @PutMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente edit(@PathVariable("id") Integer id,
                     @Valid @RequestBody Cliente cliente) {
        
        return service.save(cliente); 
    }
    
    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Integer id) {
        
        service.delete(id);
    }
    
    
}
