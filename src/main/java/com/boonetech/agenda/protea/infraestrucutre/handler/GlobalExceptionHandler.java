package com.boonetech.agenda.protea.infraestrucutre.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Erros de validação do Bean Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> lidarComValidacao(MethodArgumentNotValidException e) {
        var erros = e.getBindingResult().getFieldErrors().stream()
                .map(err ->err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    // Erros de lógica de negócio
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> lidarComErrosDeNegocio(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
