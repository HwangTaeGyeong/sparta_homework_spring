package com.sparta.personal_homework_spring.comment.controller;

import com.sparta.personal_homework_spring.auth.security.UserDetailsImpl;
import com.sparta.personal_homework_spring.comment.dto.CommentRequestDto;
import com.sparta.personal_homework_spring.comment.dto.CommentResponseDto;
import com.sparta.personal_homework_spring.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/todo/{id}/comment")
    public CommentResponseDto createComment(@PathVariable Long id,
                                            @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.addComment(id, requestDto, userDetails.getUser());
    }

    @GetMapping("/api/todo/{id}/comment")
    public List<CommentResponseDto> getComments(@PathVariable Long id,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<CommentResponseDto> comment = commentService.getComment(id, userDetails.getUser());
        return comment;
    }

    @PutMapping("/api/todo/{id}/comment/{commentId}")
    public CommentResponseDto updateComment(
            @PathVariable Long id,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.modifyComment(id, commentId, requestDto, userDetails.getUser());
    }

    @DeleteMapping("/api/todo/{id}/comment/{commentId}")
    public void deleteComment(@PathVariable Long id,
                              @PathVariable Long commentId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.delete(id, commentId, userDetails.getUser());
    }
}
