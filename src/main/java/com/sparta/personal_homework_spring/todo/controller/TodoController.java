package com.sparta.personal_homework_spring.todo.controller;

import com.sparta.personal_homework_spring.todo.dto.TodoRequestDto;
import com.sparta.personal_homework_spring.todo.dto.TodoResponseDto;
import com.sparta.personal_homework_spring.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
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
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.addTodo(requestDto);
    }

    // 할일 조회
    @GetMapping("/todo")
    public List<TodoResponseDto> getTodo() {
        return todoService.getTodos();
    }

    // 할일 업데이트
    @PutMapping("/todo/{id}")
    public TodoResponseDto updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return todoService.modifyTodo(id, requestDto);
    }

    // 할일 삭제
    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
    }
}
