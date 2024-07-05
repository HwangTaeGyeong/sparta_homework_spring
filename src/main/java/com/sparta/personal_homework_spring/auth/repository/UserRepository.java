package com.sparta.personal_homework_spring.auth.repository;

import com.sparta.personal_homework_spring.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.todoLikes WHERE u.id = :userId")
    User findByIdWithTodoLikes(@Param("userId") Long userId);
}
