package dev.rodrigo.toDoList.service;

import org.springframework.stereotype.Service;

import dev.rodrigo.toDoList.dto.TodoRequestDto;
import dev.rodrigo.toDoList.model.Todo;
import dev.rodrigo.toDoList.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    
    private final TodoRepository todoRepository;
    private final TodoRequestDto todoRequestDto;
    private final Todo todo;

    public Todo createTodo(TodoRequestDto todoRequestDto) {
        todo.setTitle(todoRequestDto.title());
        todo.setDescription(todoRequestDto.description());
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    public Todo updateTodo(String id, TodoRequestDto todoRequestDto) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        existingTodo.setTitle(todoRequestDto.title());
        existingTodo.setDescription(todoRequestDto.description());
        existingTodo.setCompleted(todoRequestDto.completed());
        return todoRepository.save(existingTodo);
    }

    public void deleteTodo(String id) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        todoRepository.delete(existingTodo);
    }

    public Todo getTodoById(String id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
    }



}
