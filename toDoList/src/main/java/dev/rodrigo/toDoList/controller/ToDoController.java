package dev.rodrigo.toDoList.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import dev.rodrigo.toDoList.dto.ToDoRequestDto;
import dev.rodrigo.toDoList.dto.ToDoResponseDto;
import dev.rodrigo.toDoList.service.ToDoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class ToDoController {

    private final ToDoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity<ToDoResponseDto> getToDoById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodoById(id));
    }

    @PostMapping
    public ResponseEntity<ToDoResponseDto> createToDo(@RequestBody ToDoRequestDto todoRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(todoRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoResponseDto> updateToDo(@PathVariable("id") String id, @RequestBody ToDoRequestDto todoRequestDto) {
        return ResponseEntity.ok().body(todoService.updateTodo(id, todoRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable("id") String id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

}
