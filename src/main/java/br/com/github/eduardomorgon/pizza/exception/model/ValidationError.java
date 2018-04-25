package br.com.github.eduardomorgon.pizza.exception.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ValidationError {

    private String field;
    private String error;
}
