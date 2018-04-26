package br.com.github.eduardomorgon.pizza.controller;

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


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.github.eduardomorgon.pizza.model.Pizza;
import br.com.github.eduardomorgon.pizza.service.PizzaService;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService service;

    @Autowired
    public PizzaController(PizzaService service) {
        this.service = service;
    }

    /*
    {
        "id": 1,
        "nome": "Mussarela",
        "descricao": "Pizza de mussarela, com rodelas de tomates",
        "precos": {
            "BROTO": 18,
            "GRANDE": 30,
            "MEDIA": 24
        }
    } 
*/

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Iterable<Pizza> all() {
        
        return service.all();
    }
    
    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Pizza find(@PathVariable Integer id) {
        
        return service.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Pizza pizza, HttpServletRequest request, HttpServletResponse response) {
        
        Pizza pizzaCreated = service.save(pizza);
        response.setHeader("Location", request.getRequestURL().append(pizzaCreated.getId()).toString());
    }
    
    @PutMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pizza edit(@PathVariable("id") Integer id,
                     @RequestBody Pizza pizza) {
       
        return service.save(pizza); 
    }
    
    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Integer id) {
        
        service.delete(id);
    }
    
}