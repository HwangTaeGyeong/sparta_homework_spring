package com.sparta.personal_homework_spring.todo.dto;

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
    private LocalDate createAt;

    public TodoResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.manager = todo.getManager();
        this.createAt = todo.getCreateAt();
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