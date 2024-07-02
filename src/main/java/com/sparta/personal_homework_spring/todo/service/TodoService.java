package com.sparta.personal_homework_spring.todo.service;

import com.sparta.personal_homework_spring.todo.dto.TodoRequestDto;
import com.sparta.personal_homework_spring.todo.dto.TodoResponseDto;
import com.sparta.personal_homework_spring.todo.entity.Todo;
import com.sparta.personal_homework_spring.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoResponseDto addTodo(TodoRequestDto requestDto) {
        // dto -> entity
        Todo savedTodo = Todo.toEntity(requestDto);

        // DB 저장
        todoRepository.save(savedTodo);

        // entity -> dto
        return TodoResponseDto.of(savedTodo);
    }

    public List<TodoResponseDto> getTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public TodoResponseDto modifyTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = todoRepository.findById(id).get();
        Todo updatedTodo = todo.update(requestDto);
        return TodoResponseDto.of(updatedTodo);
    }

    public void delete(Long id) {
        Todo todo = todoRepository.findById(id).get();
        todoRepository.delete(todo);
    }
}
