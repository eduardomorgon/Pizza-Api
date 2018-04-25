package br.com.github.eduardomorgon.pizza.exception;

import br.com.github.eduardomorgon.pizza.exception.model.MessageError;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super(MessageError.RESOURCE_NOT_FOUND.getMsg());
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}