package dev.rodrigo.toDoList.exceptions;

public class ToDoNotValidException extends RuntimeException {

    public ToDoNotValidException(){super("fill in your details correctly!");}

    public ToDoNotValidException(String message) {super(message);}
    
}
