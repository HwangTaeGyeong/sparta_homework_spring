package com.sparta.personal_homework_spring.comment.service;

import com.sparta.personal_homework_spring.auth.entity.User;
import com.sparta.personal_homework_spring.comment.dto.CommentRequestDto;
import com.sparta.personal_homework_spring.comment.dto.CommentResponseDto;
import com.sparta.personal_homework_spring.comment.entity.Comment;
import com.sparta.personal_homework_spring.comment.repository.CommentRepository;
import com.sparta.personal_homework_spring.todo.entity.Todo;
import com.sparta.personal_homework_spring.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentResponseDto addComment(Long id, CommentRequestDto requestDto, User user) {
        Todo todo = todoRepository.findById(id).get();

        // dto -> entity
        Comment savedComment = Comment.toEntity(requestDto, todo, user);

        // DB 저장
        commentRepository.save(savedComment);

        // entity -> dto
        return CommentResponseDto.of(savedComment);
    }

    public List<CommentResponseDto> getComment(Long id, User user) {
        List<Comment> comments = commentRepository.findAllByUser(user);
        List<CommentResponseDto> collect = comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
        return collect;
    }

    @Transactional
    public CommentResponseDto modifyComment(Long id, Long commentId, CommentRequestDto requestDto, User user) {
        // 할일 id 찾기
        Todo todo = todoRepository.findById(id).get();

        // 댓글 id 찾기
        Comment comment = commentRepository.findById(commentId).get();

        if (!user.getId().equals(comment.getUser().getId())) {
            throw new IllegalStateException("수정 권한이 없습니다.");
        }

        // 댓글 수정
        Comment updatedComment = comment.update(requestDto);

        return CommentResponseDto.of(updatedComment);
    }

    public void delete(Long id, Long commentId, User user) {
        Todo todo = todoRepository.findById(id).get();

        Comment comment = commentRepository.findById(commentId).get();

        if (!user.getId().equals(comment.getUser().getId())) {
            throw new IllegalStateException("수정 권한이 없습니다.");
        }

        commentRepository.delete(comment);
    }
}
