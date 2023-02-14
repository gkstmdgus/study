package com.example.hanghaeblog2.service;

import com.example.hanghaeblog2.dto.statusResponseDto;
import com.example.hanghaeblog2.dto.PostRequestDto;
import com.example.hanghaeblog2.dto.PostResponseDto;
import com.example.hanghaeblog2.entity.Member;
import com.example.hanghaeblog2.entity.Post;
import com.example.hanghaeblog2.jwt.JwtUtil;
import com.example.hanghaeblog2.repository.MemberRepository;
import com.example.hanghaeblog2.repository.PostRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public PostResponseDto postContent(PostRequestDto requestDto, HttpServletRequest request) {
        // 토큰 검사
        Member member = checkTokenValidation(request);
        // 내용 저장
        Post post = new Post(requestDto,member);
        postRepository.save(post);
        // 게시글 반환
        return new PostResponseDto(post);
    }

    @Transactional
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postDto = new ArrayList<>();
        for (Post post : posts) {
            postDto.add(new PostResponseDto(post));
        }
        return postDto;
    }

    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = checkIdHasPost(id);
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto changePost(PostRequestDto requestDto, HttpServletRequest request, Long id) {
        // 토큰 유효성
        Member member = checkTokenValidation(request);
        Post post = checkIdHasPost(id);
        post.updatePost(requestDto);
        return new PostResponseDto(post);
    }

    @Transactional
    public statusResponseDto deletePost(HttpServletRequest request, Long id) {
        checkTokenValidation(request);
        Post post = checkIdHasPost(id);
        postRepository.delete(post);
        return new statusResponseDto("게시글 삭제 성공", HttpStatus.OK);
    }

    // 토큰 유효성 / 아이디 유무 검사
    public Member checkTokenValidation(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        Member member;
        // 토큰 유무
        if (token != null) {
            // 토큰 일치 검사
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰으로 아이디 가져오기
            member = memberRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
        } else {
            return null;
        }
        return member;
    }

    // 아이디에 맞는 게시글이 있는 검사
    public Post checkIdHasPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
    }

}
