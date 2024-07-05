package com.sparta.personal_homework_spring.auth.controller;

import com.sparta.personal_homework_spring.auth.dto.UserRequestDto;
import com.sparta.personal_homework_spring.auth.security.UserDetailsImpl;
import com.sparta.personal_homework_spring.auth.service.UserService;
import com.sparta.personal_homework_spring.todo.dto.TodoResponseDto;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인
    @PostMapping("/user/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserRequestDto requestDto, HttpServletResponse response) {
        try {
            userService.login(requestDto, response);
            return ResponseEntity.ok("로그인이 성공했습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 회원가입
    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserRequestDto requestDto) {
        try {
            userService.signup(requestDto);
            return ResponseEntity.ok("회원가입이 성공했습니다.");
        } catch (DuplicateRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 내가 좋아하는 게시글 목록 조회기능
    @GetMapping("/likedTodo")
    public List<TodoResponseDto> getLikedTodo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getLikedTodo(userDetails.getUser());
    }

}