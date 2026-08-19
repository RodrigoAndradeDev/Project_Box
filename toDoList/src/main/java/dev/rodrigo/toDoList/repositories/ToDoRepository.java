package dev.rodrigo.toDoList.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.rodrigo.toDoList.model.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    Page<ToDo> findByTitle(String title, Pageable pageable);
}