package com.sparta.personal_homework_spring.diary.dto;

import lombok.Getter;

@Getter
public class DiaryRequestDto {
    private String title;
    private String contents;
    private String manager;
    private int password;
    private int date;
}


