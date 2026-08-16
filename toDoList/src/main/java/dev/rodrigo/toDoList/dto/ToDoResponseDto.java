package dev.rodrigo.toDoList.dto;

import dev.rodrigo.toDoList.model.ToDo;

public record ToDoResponseDto(
    Long id,
    String title,
    String description,
    boolean completed
) {
    public static ToDoResponseDto fromEntity(ToDo todo) {
        return new ToDoResponseDto(
            todo.getId(),
            todo.getTitle(),
            todo.getDescription(),
            todo.isCompleted()
        );
    }
    
}
