package dev.rodrigo.toDoList.service;

import org.springframework.stereotype.Service;

import dev.rodrigo.toDoList.dto.TodoRequestDto;
import dev.rodrigo.toDoList.dto.TodoResponseDto;
import dev.rodrigo.toDoList.model.Todo;
import dev.rodrigo.toDoList.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    
    private final TodoRepository todoRepository;

    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto) {
        Todo todo = new Todo();
        todo.setTitle(todoRequestDto.title());
        todo.setDescription(todoRequestDto.description());
        todo.setCompleted(false);

        Todo saved = todoRepository.save(todo);

        return TodoResponseDto.fromEntity(saved);
    }

    public TodoResponseDto updateTodo(String id, TodoRequestDto todoRequestDto) {
        Todo existingTodo = searchOrFail(id);
        existingTodo.setTitle(todoRequestDto.title());
        existingTodo.setDescription(todoRequestDto.description());
        existingTodo.setCompleted(todoRequestDto.completed());

        Todo saved = todoRepository.save(existingTodo);

        return TodoResponseDto.fromEntity(saved);
    }

    public void deleteTodo(String id) {
        if(!todoRepository.existsById(id)){
            throw new RuntimeException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }

    public TodoResponseDto getTodoById(String id) {
        return TodoResponseDto.fromEntity(searchOrFail(id));
    }

    private Todo searchOrFail(String id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
    }



}
