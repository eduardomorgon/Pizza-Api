package br.com.github.eduardomorgon.pizza.controller;

import br.com.github.eduardomorgon.pizza.model.Pedido;
import br.com.github.eduardomorgon.pizza.service.PedidoService;
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
@RequestMapping("pedidos")
public class PedidoController {
    
    private final PedidoService service;

    @Autowired
    public PedidoController(PedidoService service) {
        this.service = service;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Iterable<Pedido> all() {
        
        return service.all();
    }
    
    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Pedido find(@PathVariable Integer id) {
        
        return service.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Pedido pedido, HttpServletRequest request, HttpServletResponse response) {

        Pedido createdPedido = service.save(pedido);
        response.setHeader("Location", request.getRequestURL().append(createdPedido.getId()).toString());
    }
    
    @PutMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pedido edit(@PathVariable("id") Integer id,
                     @Valid @RequestBody Pedido pedido) {
        
        return service.save(pedido); 
    }
    
    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Integer id) {
        
        service.delete(id);
    }
    
}
