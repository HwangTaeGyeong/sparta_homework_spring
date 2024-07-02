package com.sparta.personal_homework_spring.todo.entity;

import com.sparta.personal_homework_spring.todo.dto.TodoRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;
    private String manager;
    private int password;
    private LocalDate createAt;

    @Builder
    public Todo(String title, String contents, String manager, int password) {
        this.title = title;
        this.contents = contents;
        this.manager = manager;
        this.password = password;
        this.createAt = LocalDate.now();
    }

    @Builder
    public static Todo toEntity(TodoRequestDto requestDto) {
        return Todo.builder()
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .manager(requestDto.getManager())
                .password(requestDto.getPassword())
                .build();
    }

    public Todo update(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        return this;
    }
}
