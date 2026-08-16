package dev.rodrigo.toDoList.dto;

public record ToDoUpdateRequestDto (
    String title,
    String description,
    Boolean completed
){}
