package com.example.hanghaeblog2.controller;

import com.example.hanghaeblog2.dto.fetch.GetPostDto;
import com.example.hanghaeblog2.dto.response.statusResponseDto;
import com.example.hanghaeblog2.dto.request.PostRequestDto;
import com.example.hanghaeblog2.dto.response.PostResponseDto;
import com.example.hanghaeblog2.security.UserDetailsImpl;
import com.example.hanghaeblog2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    // 전체 게시글 조회
    @GetMapping("/post")
    public List<GetPostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    // 특정 게시글 조회
    @GetMapping("/post/{id}")
    public ResponseEntity<GetPostDto> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 게시글 수정
    @PutMapping("/post/{id}")
    public ResponseEntity<PostResponseDto> changePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.changePost(requestDto, userDetails.getUsername(), id);
    }

    // 게시글 등록
    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> postContent(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.postContent(requestDto, userDetails.getUsername());
    }

    // 게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<statusResponseDto> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(userDetails.getUsername(), id);
    }

    // 게시글 좋아요
    @GetMapping("/post/like/{id}")
    public ResponseEntity<statusResponseDto> postLike(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        return postService.postLike(userDetails.getUsername(), id);
    }
}
