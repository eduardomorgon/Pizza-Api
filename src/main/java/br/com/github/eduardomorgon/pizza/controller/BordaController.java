/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.github.eduardomorgon.pizza.controller;

import br.com.github.eduardomorgon.pizza.exception.ResourceNotFoundException;
import br.com.github.eduardomorgon.pizza.model.Borda;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.github.eduardomorgon.pizza.service.BordaService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author eduardo
 */
@RestController
@RequestMapping("/bordas")
public class BordaController {

    private final BordaService service;

    @Autowired
    public BordaController(BordaService service) {
        this.service = service;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Iterable<Borda> all() {
        return service.all();
    }
    
    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Borda find(@PathVariable Integer id) {
        return service.find(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Borda borda, HttpServletRequest request, HttpServletResponse response) {
        Borda bordaCreated = service.save(borda);
        response.setHeader("Location", request.getRequestURL().append(bordaCreated.getId()).toString());
    }
    
    @PutMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void edit(@PathVariable("id") Integer id,
                     @Valid @RequestBody Borda borda) {
        
        Optional<Borda> optionalBorda = Optional.ofNullable(service.find(id));
        optionalBorda 
                .filter(b -> b.getId().equals(borda.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Borda não pode ser alterada, favor verificar se ela existe ou id passado como parametro é diferente do id do json"));
        service.save(borda); 
    }
    
    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Integer id) {
        
        Optional<Borda> optionalBorda = Optional.ofNullable(service.find(id));
        Borda borda = optionalBorda 
                .map(b -> b)
                .orElseThrow(() -> new ResourceNotFoundException("Não existe Borda com o id: "+id));
        service.delete(borda);
    }
    
}
