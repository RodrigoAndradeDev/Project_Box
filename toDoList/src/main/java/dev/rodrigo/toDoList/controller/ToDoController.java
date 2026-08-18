package dev.rodrigo.toDoList.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import dev.rodrigo.toDoList.dto.ToDoUpdateRequestDto;
import dev.rodrigo.toDoList.dto.ToDoCreateRequestDto;
import dev.rodrigo.toDoList.dto.ToDoResponseDto;
import dev.rodrigo.toDoList.service.ToDoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class ToDoController {

    private final ToDoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity<ToDoResponseDto> getToDoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(todoService.getTodoById(id));
    }

    @PostMapping
    public ResponseEntity<ToDoResponseDto> createToDo(@RequestBody ToDoCreateRequestDto todoRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(todoRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoResponseDto> updateToDo(@PathVariable("id") Long id, @RequestBody ToDoUpdateRequestDto todoRequestDto) {
        return ResponseEntity.ok().body(todoService.updateTodo(id, todoRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<ToDoResponseDto> getAllToDos(@RequestParam(required = false, defaultValue = "1") int pageNo,
                                             @RequestParam(required = false, defaultValue = "5") int pageSize,
                                             @RequestParam(required = false, defaultValue = "id") String sortBy,
                                             @RequestParam(required = false, defaultValue = "ASC") String sortDir){



        Sort sort;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortBy).ascending();
        } else{
            sort = Sort.by(sortBy).descending();
        }
    return todoService.fetchAllToDo(PageRequest.of(pageNo-1, pageSize, sort));

        
    }

}
