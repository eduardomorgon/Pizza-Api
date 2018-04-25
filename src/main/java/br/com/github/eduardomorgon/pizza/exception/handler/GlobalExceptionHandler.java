package br.com.github.eduardomorgon.pizza.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.github.eduardomorgon.pizza.exception.ResourceNotFoundException;
import br.com.github.eduardomorgon.pizza.exception.model.ResponseError;
import br.com.github.eduardomorgon.pizza.exception.model.ValidationError;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        
        ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest wr) {
        
        ResponseError responseError = ResponseError.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY.name())
//                .message(MessageError.VALIDATION_ERROR.getMsg())
                .messages(getValidationsErros(ex.getBindingResult().getFieldErrors()))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseError, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    private List<ValidationError> getValidationsErros(List<FieldError> fildErrors) {
        
        return fildErrors.stream().map(e -> {
                    return ValidationError.builder()
                            .error(e.getDefaultMessage())
                            .field(e.getField())
                            .build();
                }).collect(Collectors.toList());
    }
    
}