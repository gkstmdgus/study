package com.example.hanghaeblog2.controller;

import com.example.hanghaeblog2.dto.statusResponseDto;
import com.example.hanghaeblog2.dto.PostRequestDto;
import com.example.hanghaeblog2.dto.PostResponseDto;
import com.example.hanghaeblog2.exception.customException.TokenException;
import com.example.hanghaeblog2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    @GetMapping("/post")
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/post/{id}")
    public PostResponseDto changePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.changePost(requestDto, request, id);
    }

    @PostMapping("/post")
    public PostResponseDto postContent(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.postContent(requestDto, request);
    }

    @DeleteMapping("/post/{id}")
    public statusResponseDto deletePost(@PathVariable Long id, HttpServletRequest request) {
        return postService.deletePost(request, id);
    }
}
