package com.example.hanghaeblog2.controller;

import com.example.hanghaeblog2.dto.CommentRequestDto;
import com.example.hanghaeblog2.dto.CommentResponseDto;
import com.example.hanghaeblog2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public CommentResponseDto postComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request, @PathVariable Long id) {
        return commentService.postComment(requestDto, request, id);
    }

    @PutMapping("/comment/{comment}")
    public CommentResponseDto updateComment(HttpServletRequest request, @RequestBody CommentRequestDto requestDto, @PathVariable Long comment) {
        return commentService.updateComment(request,requestDto,comment);
    }
}
