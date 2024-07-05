package com.sparta.personal_homework_spring.todo.entity;

import com.sparta.personal_homework_spring.auth.entity.User;
import com.sparta.personal_homework_spring.comment.entity.Comment;
import com.sparta.personal_homework_spring.like.entity.TodoLike;
import com.sparta.personal_homework_spring.todo.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private Long id;

    private String title;
    private String contents;
    private String manager;
    private int password;
    private LocalDate createAt;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "todo")
    private List<Comment> comment;

    @OneToMany(mappedBy = "todo")
    private List<TodoLike> todoLikes;

    @Builder
    public Todo(String title, String contents, String manager, int password, User user) {
        this.title = title;
        this.contents = contents;
        this.manager = manager;
        this.password = password;
        this.user = user;
        this.createAt = LocalDate.now();
    }

    @Builder
    public static Todo toEntity(TodoRequestDto requestDto, User user) {
        return Todo.builder()
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .manager(requestDto.getManager())
                .password(requestDto.getPassword())
                .user(user)
                .build();
    }

    public Todo update(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        return this;
    }
}
