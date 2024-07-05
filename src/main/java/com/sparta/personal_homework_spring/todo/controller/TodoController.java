package com.sparta.personal_homework_spring.todo.controller;

import com.sparta.personal_homework_spring.auth.security.UserDetailsImpl;
import com.sparta.personal_homework_spring.todo.dto.TodoRequestDto;
import com.sparta.personal_homework_spring.todo.dto.TodoResponseDto;
import com.sparta.personal_homework_spring.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    // 할일 저장
    @PostMapping("/todo")
    @ResponseBody
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todoService.addTodo(requestDto, userDetails.getUser());
    }

    // 할일 조회
    @GetMapping("/todo")
    public List<TodoResponseDto> getTodo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todoService.getTodos(userDetails.getUser());
    }

    // 할일 업데이트
    @PutMapping("/todo/{id}")
    public TodoResponseDto updateTodo(@PathVariable Long id,
                                      @RequestBody TodoRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todoService.modifyTodo(id, requestDto, userDetails.getUser());
    }

    // 할일 삭제
    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable Long id,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        todoService.delete(id, userDetails.getUser());
    }
}
