package com.sparta.personal_homework_spring.like.repository;

import com.sparta.personal_homework_spring.auth.entity.User;
import com.sparta.personal_homework_spring.like.entity.TodoLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<TodoLike, Long> {
    Optional<TodoLike> findByTodoIdAndUserId(Long todoId, Long userId);
}
