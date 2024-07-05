package com.sparta.personal_homework_spring.like.service;

import com.sparta.personal_homework_spring.auth.entity.User;
import com.sparta.personal_homework_spring.like.entity.LikeStatus;
import com.sparta.personal_homework_spring.like.entity.TodoLike;
import com.sparta.personal_homework_spring.like.repository.LikeRepository;
import com.sparta.personal_homework_spring.todo.entity.Todo;
import com.sparta.personal_homework_spring.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final TodoService todoService;

    @Transactional
    public TodoLike toggleLike(Long todoId, User user) {
        // 게시물(할일)이 있는지 확인
        Todo todo = todoService.findById(todoId);

        Optional<TodoLike> existTodoLike = likeRepository.findByTodoIdAndUserId(todoId, user.getId());

        // 좋아요 객체가 없으면 좋아요 생성
        if (existTodoLike.isEmpty()) {
            TodoLike todoLike = new TodoLike(todo, user);
            likeRepository.save(todoLike);
            return todoLike;
        } else {
            // 좋아요가 객체가 있다면 좋아요 상태 변경
            if (existTodoLike.get().getStatus().equals(LikeStatus.CANCEL)) {
                return doLike(existTodoLike.get()); // 취소된 좋아요이거나 신규 좋아요인 경우 좋아요
            } else {
                return cancelLike(existTodoLike.get()); // 이미 좋아요 상태인 경우 좋아요 취소
            }
        }
    }

    // 좋아요
    private TodoLike doLike(TodoLike like) {
        return like.doLike();
    }

    // 좋아요 취소
    private TodoLike cancelLike(TodoLike like) {
        return like.cancelLike();
    }
}
