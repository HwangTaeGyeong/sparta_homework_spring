package com.sparta.personal_homework_spring.diary.entity;

import com.sparta.personal_homework_spring.diary.dto.DiaryRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Diary {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private int password;
    private int date;

    public Diary(DiaryRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }

    public void update(DiaryRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
    }
}
