package com.sparta.personal_homework_spring.todo.service;

import com.sparta.personal_homework_spring.auth.entity.User;
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

    public TodoResponseDto addTodo(TodoRequestDto requestDto, User user) {
        // dto -> entity
        Todo savedTodo = Todo.toEntity(requestDto, user);

        // DB 저장
        todoRepository.save(savedTodo);

        // entity -> dto
        return TodoResponseDto.of(savedTodo);
    }

    public List<TodoResponseDto> getTodos(User user) {
        List<Todo> todos = todoRepository.findAllByUser(user);
        return todos.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public TodoResponseDto modifyTodo(Long id, TodoRequestDto requestDto, User user) {
        // 게시물(할일)이 있는지 확인
        Todo todo = findById(id);

        // user 가 만든 일정의 id 가 맞는지 확인
        if (!user.getId().equals(todo.getUser().getId())) {
            throw new IllegalStateException("수정할 권한이 없습니다.");
        }

        Todo updatedTodo = todo.update(requestDto);
        return TodoResponseDto.of(updatedTodo);
    }

    public void delete(Long id, User user) {
        // 게시물(할일)이 있는지 확인
        Todo todo = findById(id);

        // user 가 만든 일정의 id 가 맞는지 확인
        if (!user.getId().equals(todo.getUser().getId())) {
            throw new IllegalStateException("삭제할 권한이 없습니다.");
        }

        todoRepository.delete(todo);
    }

    public Todo findById(Long id) {
        return todoRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Todo 가 존재하지 않습니다.")
        );
    }
}
