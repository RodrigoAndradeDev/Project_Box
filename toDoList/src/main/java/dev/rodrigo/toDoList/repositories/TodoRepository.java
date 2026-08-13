package dev.rodrigo.toDoList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.rodrigo.toDoList.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {
    
}
