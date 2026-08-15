package dev.rodrigo.toDoList.service;

import org.springframework.stereotype.Service;

import dev.rodrigo.toDoList.dto.ToDoRequestDto;
import dev.rodrigo.toDoList.dto.ToDoResponseDto;
import dev.rodrigo.toDoList.model.ToDo;
import dev.rodrigo.toDoList.repositories.ToDoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToDoService  extends RuntimeException {
    
    private final ToDoRepository todoRepository;

    public ToDoResponseDto createTodo(ToDoRequestDto todoRequestDto) {
        ToDo todo = new ToDo();
        todo.setTitle(todoRequestDto.title());
        todo.setDescription(todoRequestDto.description());
        todo.setCompleted(false);
        ToDo saved = todoRepository.save(todo);
        return ToDoResponseDto.fromEntity(saved);
    }

    public ToDoResponseDto updateTodo(String id, ToDoRequestDto todoRequestDto) {
        ToDo existingTodo = searchOrFail(id);
        existingTodo.setTitle(todoRequestDto.title());
        existingTodo.setDescription(todoRequestDto.description());
        existingTodo.setCompleted(todoRequestDto.completed());
        ToDo saved = todoRepository.save(existingTodo);
        return ToDoResponseDto.fromEntity(saved);
    }

    public void deleteTodo(String id) {
        if(!todoRepository.existsById(id)){
            throw new RuntimeException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }

    public ToDoResponseDto getTodoById(String id) {
        return ToDoResponseDto.fromEntity(searchOrFail(id));
    }

    private ToDo searchOrFail(String id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
    }
}
