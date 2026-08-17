package dev.rodrigo.toDoList.exceptions;

public class ToDoNotFoundException extends RuntimeException {
    public ToDoNotFoundException(){super("Todo not found");}
    
    public ToDoNotFoundException(String message){super(message);}
}
