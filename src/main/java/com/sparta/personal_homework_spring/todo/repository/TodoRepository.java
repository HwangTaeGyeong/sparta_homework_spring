package com.sparta.personal_homework_spring.todo.repository;

import com.sparta.personal_homework_spring.auth.entity.User;
import com.sparta.personal_homework_spring.like.entity.TodoLike;
import com.sparta.personal_homework_spring.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUser(User user);
}
