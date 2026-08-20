package dev.rodrigo.toDoList.infra.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import dev.rodrigo.toDoList.exceptions.ToDoNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorMessage> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();

        RestErrorMessage errorMessage = RestErrorMessage.ofValidation(
                request.getRequestURI(),
                errors
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(errorMessage);
    }

    @ExceptionHandler(ToDoNotFoundException.class)
    public ResponseEntity<RestErrorMessage> toDoNotFoundExceptionHandler(
            ToDoNotFoundException e,
            HttpServletRequest request
    ) {
        RestErrorMessage errorMessage = RestErrorMessage.of(
                "about:blank",
                "Resource not found",
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestErrorMessage> runtimeExceptionHandler(
            RuntimeException e,
            HttpServletRequest request
    ) {
        RestErrorMessage errorMessage = RestErrorMessage.of(
                "about:blank",
                "Internal server error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}