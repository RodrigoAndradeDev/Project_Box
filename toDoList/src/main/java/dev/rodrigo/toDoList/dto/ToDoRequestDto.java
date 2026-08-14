package dev.rodrigo.toDoList.dto;

public record ToDoRequestDto (
    String title,
    String description,
    boolean completed
){}
