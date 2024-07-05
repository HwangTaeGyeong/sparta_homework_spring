package com.sparta.personal_homework_spring.like.dto;

import com.sparta.personal_homework_spring.like.entity.LikeStatus;
import com.sparta.personal_homework_spring.like.entity.TodoLike;
import lombok.Getter;

@Getter
public class LikeResponseDto {
    private final Long id;
    private final LikeStatus status; // 좋아요 상태 [LIKED, CANCELED]
    private final Long userId;

    public LikeResponseDto(TodoLike like) {
        this.id = like.getId();
        this.status = like.getStatus();
        this.userId = like.getUser().getId();
    }
}
