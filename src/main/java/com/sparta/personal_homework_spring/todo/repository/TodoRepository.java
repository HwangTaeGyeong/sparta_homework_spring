package com.sparta.personal_homework_spring.todo.repository;

import com.sparta.personal_homework_spring.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
