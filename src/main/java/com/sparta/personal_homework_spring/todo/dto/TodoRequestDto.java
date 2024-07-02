package com.sparta.personal_homework_spring.todo.dto;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String title;
    private String contents;
    private String manager;
    private int password;
}


