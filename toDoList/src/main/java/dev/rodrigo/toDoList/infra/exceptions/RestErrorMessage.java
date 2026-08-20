package dev.rodrigo.toDoList.infra.exceptions;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.validation.FieldError;

public record RestErrorMessage (String type, String title, int status, String detail, String instance, OffsetDateTime timestamp, List<FieldError> errors) {
    

    public static RestErrorMessage of(String type, String title, int status, String detail, String instance){
        return new RestErrorMessage(type, title, status, detail, instance, OffsetDateTime.now(), null);
    }

    public static RestErrorMessage ofValidation(String instance, List<FieldError> errors){
        return new RestErrorMessage(
            "about:blank",
            "Validation error",
            422,
            "one or more fields are invalid",
            instance,
            OffsetDateTime.now(),
            errors
        );
    }
    
}
