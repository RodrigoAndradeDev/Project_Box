package dev.rodrigo.toDoList.dto;

public record TodoResponseDto(
    String id,
    String title,
    String description,
    boolean completed
) {
    
}
