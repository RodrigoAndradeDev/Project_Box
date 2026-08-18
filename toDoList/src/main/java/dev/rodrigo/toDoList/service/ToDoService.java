package dev.rodrigo.toDoList.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.rodrigo.toDoList.dto.ToDoUpdateRequestDto;
import dev.rodrigo.toDoList.exceptions.ToDoNotFoundException;
import dev.rodrigo.toDoList.dto.ToDoCreateRequestDto;
import dev.rodrigo.toDoList.dto.ToDoResponseDto;
import dev.rodrigo.toDoList.model.ToDo;
import dev.rodrigo.toDoList.repositories.ToDoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToDoService {
    
    private final ToDoRepository todoRepository;

    public ToDoResponseDto createTodo(ToDoCreateRequestDto todoRequestDto) {
        ToDo todo = new ToDo();
        todo.setTitle(todoRequestDto.title());
        todo.setDescription(todoRequestDto.description());
        todo.setCompleted(false);
        ToDo saved = todoRepository.save(todo);
        return ToDoResponseDto.fromEntity(saved);
    }

    public ToDoResponseDto updateTodo(Long id, ToDoUpdateRequestDto todoRequestDto) {
        ToDo existingTodo = findOrThrow(id);
        existingTodo.setTitle(todoRequestDto.title());
        existingTodo.setDescription(todoRequestDto.description());
        existingTodo.setCompleted(todoRequestDto.completed());
        ToDo saved = todoRepository.save(existingTodo);
        return ToDoResponseDto.fromEntity(saved);
    }

    public void deleteTodo(Long id) {
        if(!todoRepository.existsById(id)){
            throw new ToDoNotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }

    public ToDoResponseDto getTodoById(Long id) {
        return ToDoResponseDto.fromEntity(findOrThrow(id));
    }

    public List<ToDoResponseDto> fetchAllToDo(Pageable pageable) {
        return todoRepository.findAll(pageable)
            .map(ToDoResponseDto::fromEntity)
            .getContent();
    }

    private ToDo findOrThrow(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new ToDoNotFoundException("Todo not found with id: " + id));
    }
}
