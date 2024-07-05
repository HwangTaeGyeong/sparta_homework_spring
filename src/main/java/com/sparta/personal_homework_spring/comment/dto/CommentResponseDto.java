package com.sparta.personal_homework_spring.comment.dto;

import com.sparta.personal_homework_spring.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommentResponseDto {
    private String username;
    private String contents;

    public CommentResponseDto(Comment comment) {
        this.username = comment.getUserName();
        this.contents = comment.getContents();
    }

    public static CommentResponseDto of(Comment comment) {
        return CommentResponseDto.builder()
                .username(comment.getUserName())
                .contents(comment.getContents())
                .build();
    }
}
