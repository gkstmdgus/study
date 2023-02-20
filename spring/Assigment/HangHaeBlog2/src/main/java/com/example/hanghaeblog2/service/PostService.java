package com.example.hanghaeblog2.service;

import com.example.hanghaeblog2.dto.response.statusResponseDto;
import com.example.hanghaeblog2.dto.request.PostRequestDto;
import com.example.hanghaeblog2.dto.response.PostResponseDto;
import com.example.hanghaeblog2.entity.Member;
import com.example.hanghaeblog2.entity.Post;
import com.example.hanghaeblog2.entity.UserRole;
import com.example.hanghaeblog2.entity.like.PostLike;
import com.example.hanghaeblog2.exception.customException.AuthorityException;
import com.example.hanghaeblog2.exception.customException.NoPostException;
import com.example.hanghaeblog2.exception.customException.TokenException;
import com.example.hanghaeblog2.exception.customException.UnknownException;
import com.example.hanghaeblog2.jwt.JwtUtil;
import com.example.hanghaeblog2.repository.CommentRepository;
import com.example.hanghaeblog2.repository.MemberRepository;
import com.example.hanghaeblog2.repository.PostLikeRepository;
import com.example.hanghaeblog2.repository.PostRepository;
import com.example.hanghaeblog2.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;

    //== 비즈니스 로직 ==//

    // 게시글 등록
    @Transactional
    public ResponseEntity<PostResponseDto> postContent(PostRequestDto requestDto, String username){
        // 토큰 검사
//        Member member = jwtUtil.checkTokenValidation(request);
        // member 반환
        Member member = memberRepository.findByUsername(username).orElseThrow(UnknownException::new);
        // 내용 저장
        Post post = new Post(requestDto,member);
        postRepository.save(post);
        // 게시글 반환
        return ResponseEntity.ok(new PostResponseDto(post));
    }

    // 모든 게시글 조회
    @Transactional
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postDto = new ArrayList<>();
        for (Post post : posts) {
            postDto.add(new PostResponseDto(post, commentRepository.findCommentByPostOrderByCreatedAtDesc(post)));
        }
        return postDto;
    }

    // 특정 게시글 조회
    @Transactional
    public ResponseEntity<PostResponseDto> getPost(Long id) {
        Post post = checkIdHasPost(id);
        return ResponseEntity.ok(new PostResponseDto(post, post.getComments()));
    }

    // 게시글 변경
    @Transactional
    public ResponseEntity<PostResponseDto> changePost(PostRequestDto requestDto, String username, Long id) {
        // 토큰 유효성
//        Member member = jwtUtil.checkTokenValidation(request);
        // 멤버 찾기
        Member member = memberRepository.findByUsername(username).orElseThrow(UnknownException::new);
        // 게시글 유효성
        Post post = checkUpdate(member,id);
        // 업데이트
        post.updatePost(requestDto);
        return ResponseEntity.ok(new PostResponseDto(post));
    }

    // 게시글 삭제
    @Transactional
    public ResponseEntity<statusResponseDto> deletePost(String username, Long id) {
        // 토큰 유효성
//        Member member = jwtUtil.checkTokenValidation(request);
        // 멤버 찾기
        Member member = memberRepository.findByUsername(username).orElseThrow(UnknownException::new);
        // 게시글 유효성
        Post post = checkUpdate(member, id);
        // 업데이트
        postRepository.delete(post);
        return ResponseEntity.ok(new statusResponseDto("게시글 삭제 성공", HttpStatus.OK));
    }

    // 게시글 좋아요
    @Transactional
    public ResponseEntity<statusResponseDto> postLike(String username, Long id) {
        // 게시글 좋아요 객체가 있는지 확인
        Member member = memberRepository.findByUsername(username).orElseThrow(UnknownException::new);
        PostLike hasLike = postLikeRepository.findByMember_IdAndPost_Id(member.getId(), id);
        // 없으면 하나 만들어서 입력
        String state = "";
        if (hasLike == null) {
            Post post = postRepository.findById(id).orElseThrow(NoPostException::new);
            PostLike like = new PostLike(post, member);
            postLikeRepository.save(like);
            state = "게시글 좋아요 등록";
        } else{
            // 있으면 엔티티 삭제
            postLikeRepository.delete(hasLike);
            state = "게시글 좋아요 취소";
        }
        return ResponseEntity.ok(new statusResponseDto(state, HttpStatus.OK));
    }

    //== 반복 로직 ==//

    // 게시글 유효성 검사
    public Post checkUpdate(Member member, Long id) {
        // id로 게시글 찾기 (유무 확인)
        Post post = checkIdHasPost(id);
        // 권한 확인
        if (!(member.getRole() == UserRole.ADMIN || member.getId() == post.getMember().getId())) {
            throw new AuthorityException("권한이 없습니다.");
        }
        return post;
    }

    // id로 게시글 찾기
    public Post checkIdHasPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new NoPostException("게시글이 존재하지 않습니다.")
        );
    }

}
