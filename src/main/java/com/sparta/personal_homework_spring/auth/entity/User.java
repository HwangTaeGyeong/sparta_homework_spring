package com.sparta.personal_homework_spring.auth.entity;

import com.sparta.personal_homework_spring.auth.dto.UserRequestDto;
import com.sparta.personal_homework_spring.comment.entity.Comment;
import com.sparta.personal_homework_spring.like.entity.TodoLike;
import com.sparta.personal_homework_spring.todo.entity.Todo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<Todo> todo;

    @OneToMany(mappedBy = "user")
    private List<Comment> comment;

    @OneToMany(mappedBy = "user")
    private List<TodoLike> todoLikes;


    @Builder
    public User(String username, String password, String email) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.role = UserRoleEnum.USER;
    }

    @Builder
    public static User toEntity(UserRequestDto requestDto, String password) {
        return User.builder()
                .username(requestDto.getUsername())
                .password(password)
                .email(requestDto.getEmail())
                .build();
    }

}