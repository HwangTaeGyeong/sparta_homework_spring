package com.sparta.personal_homework_spring.diary.dto;

import com.sparta.personal_homework_spring.diary.entity.Diary;
import lombok.Getter;

@Getter
public class DiaryResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String manager;
//    private int password;
    private int date;

    public DiaryResponseDto(Diary diary) {
        this.id = diary.getId();
        this.title = diary.getTitle();
        this.contents = diary.getContents();
        this.manager = diary.getManager();
//        this.password = diary.getPassword();
        this.date = diary.getDate();
    }
}