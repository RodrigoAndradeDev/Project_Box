package dev.rodrigo.toDoList.dto;

import dev.rodrigo.toDoList.model.Todo;

public record TodoResponseDto(
    String id,
    String title,
    String description,
    boolean completed
) {
    public static TodoResponseDto fromEntity(Todo todo) {
        return new TodoResponseDto(
            todo.getId(),
            todo.getTitle(),
            todo.getDescription(),
            todo.isCompleted()
        );
    }
    
}
