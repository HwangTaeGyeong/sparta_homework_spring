package com.sparta.personal_homework_spring.todo.dto;

import com.sparta.personal_homework_spring.like.entity.TodoLike;
import com.sparta.personal_homework_spring.todo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class TodoResponseDto {
    private String title;
    private String contents;
    private String manager;
    private int todoLikes;
    private LocalDate createAt;

    public TodoResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.manager = todo.getManager();
        this.todoLikes = todo.getTodoLikes().size();
        this.createAt = todo.getCreateAt();
    }

    public TodoResponseDto(TodoLike todoLike) {
        this.title = todoLike.getTodo().getTitle();
        this.contents = todoLike.getTodo().getContents();
        this.manager = todoLike.getTodo().getManager();
        this.todoLikes = todoLike.getTodo().getTodoLikes().size();
        this.createAt = todoLike.getTodo().getCreateAt();
    }

    public static TodoResponseDto of(Todo todo) {
        return TodoResponseDto.builder()
                .title(todo.getTitle())
                .contents(todo.getContents())
                .manager(todo.getManager())
                .createAt(todo.getCreateAt())
                .build();
    }

}