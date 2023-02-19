package com.example.hanghaeblog2.controller;

import com.example.hanghaeblog2.dto.request.CommentRequestDto;
import com.example.hanghaeblog2.dto.response.CommentResponseDto;
import com.example.hanghaeblog2.dto.response.statusResponseDto;
import com.example.hanghaeblog2.security.UserDetailsImpl;
import com.example.hanghaeblog2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 등록
    @PostMapping("/comment/{id}")
    public CommentResponseDto postComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        return commentService.postComment(requestDto, userDetails.getUsername(), id);
    }

    // 댓글 수정
    @PutMapping("/comment/{comment}")
    public CommentResponseDto updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto, @PathVariable Long comment) {
        return commentService.updateComment(userDetails.getUsername(),requestDto,comment);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{comment}")
    public statusResponseDto deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long comment) {
        return commentService.deleteService(userDetails.getUsername(), comment);
    }

    // 댓글 좋아요
    @GetMapping("/comment/like/{comment}")
    public statusResponseDto postLike(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long comment) {
        return commentService.commentLike(userDetails.getUsername(), comment);
    }
}
