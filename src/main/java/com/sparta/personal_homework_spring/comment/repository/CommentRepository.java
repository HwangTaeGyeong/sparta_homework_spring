package com.sparta.personal_homework_spring.comment.repository;


import com.sparta.personal_homework_spring.auth.entity.User;
import com.sparta.personal_homework_spring.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTodoId(Long todoId);

    List<Comment> findAllByUser(User user);
}
