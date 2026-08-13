package dev.rodrigo.toDoList.dto;

public record TodoRequestDto (
    String title,
    String description,
    boolean completed
){}
