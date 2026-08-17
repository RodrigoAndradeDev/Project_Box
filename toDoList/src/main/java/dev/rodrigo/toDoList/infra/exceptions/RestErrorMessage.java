package dev.rodrigo.toDoList.infra.exceptions;

import org.springframework.http.HttpStatus;

public record RestErrorMessage (HttpStatus status, String message) {
    
}
