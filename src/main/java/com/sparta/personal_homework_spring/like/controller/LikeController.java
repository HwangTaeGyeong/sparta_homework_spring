package com.sparta.personal_homework_spring.like.controller;

import com.sparta.personal_homework_spring.auth.security.UserDetailsImpl;
import com.sparta.personal_homework_spring.like.dto.LikeResponseDto;
import com.sparta.personal_homework_spring.like.entity.TodoLike;
import com.sparta.personal_homework_spring.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/todo/{todoId}/like")
    public LikeResponseDto toggleTodoLike(@PathVariable Long todoId,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoLike like = likeService.toggleLike(todoId, userDetails.getUser());
        return new LikeResponseDto(like);
    }
}
