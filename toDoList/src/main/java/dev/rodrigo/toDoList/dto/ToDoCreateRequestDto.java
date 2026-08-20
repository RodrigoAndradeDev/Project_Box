package dev.rodrigo.toDoList.dto;

import jakarta.validation.constraints.NotBlank;

public record ToDoCreateRequestDto (
    
    @NotBlank(message = "title is required!")
    String title, 
    
    @NotBlank(message = "description is required!")
    String description) {
    
}
