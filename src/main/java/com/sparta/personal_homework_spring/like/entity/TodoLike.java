package com.sparta.personal_homework_spring.like.entity;

import com.sparta.personal_homework_spring.auth.entity.User;
import com.sparta.personal_homework_spring.todo.entity.Todo;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TodoLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LikeStatus status; // 좋아요 상태 [LIKED, CANCELED]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Todo todo;

    public TodoLike(Todo todo, User user) {
        this.todo = todo;
        this.status = LikeStatus.LIKE;
        this.user = user;
    }

    public TodoLike doLike() {
        this.status = LikeStatus.LIKE;
        return this;
    }

    public TodoLike cancelLike() {
        this.status = LikeStatus.CANCEL;
        return this;
    }
}
