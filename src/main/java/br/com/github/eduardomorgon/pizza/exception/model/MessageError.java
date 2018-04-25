package br.com.github.eduardomorgon.pizza.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageError {

    RESOURCE_NOT_FOUND("Resource Not found"),
    METHOD_NOT_ALLOWED("Method Not Allowed"),
    VALIDATION_ERROR("Validation Error");

    private final String msg;
}
