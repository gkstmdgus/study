package com.example.hanghaeblog2.controller;

import com.example.hanghaeblog2.dto.request.CommentRequestDto;
import com.example.hanghaeblog2.dto.response.CommentResponseDto;
import com.example.hanghaeblog2.dto.response.statusResponseDto;
import com.example.hanghaeblog2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 등록
    @PostMapping("/comment/{id}")
    public CommentResponseDto postComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id) {
        return commentService.postComment(requestDto, request, id);
    }

    // 댓글 수정
    @PutMapping("/comment/{comment}")
    public CommentResponseDto updateComment(HttpServletRequest request, @RequestBody CommentRequestDto requestDto, @PathVariable Long comment) {
        return commentService.updateComment(request,requestDto,comment);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{comment}")
    public statusResponseDto deleteComment(HttpServletRequest request, @PathVariable Long comment) {
        return commentService.deleteService(request, comment);
    }
}
