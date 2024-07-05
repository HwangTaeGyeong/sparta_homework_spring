package com.sparta.personal_homework_spring.comment.entity;


import com.sparta.personal_homework_spring.auth.entity.User;
import com.sparta.personal_homework_spring.comment.dto.CommentRequestDto;
import com.sparta.personal_homework_spring.todo.entity.Todo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    private String userName;
    private String contents;
    private LocalDate createAt;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Todo todo;

    @Builder
    public Comment(String userName, String contents, Todo todo, User user) {
        this.userName = userName;
        this.contents = contents;
        this.user = user;
        this.todo = todo;
        this.createAt = LocalDate.now();
    }

    @Builder
    public static Comment toEntity(CommentRequestDto requestDto, Todo todo, User user) {
        return Comment.builder()
                .userName(requestDto.getUsername())
                .contents(requestDto.getContents())
                .user(user)
                .todo(todo)
                .build();
    }

    public Comment update(CommentRequestDto requestDto) {
        this.userName = requestDto.getUsername();
        this.contents = requestDto.getContents();
        return this;
    }

}
