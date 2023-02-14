package com.example.hanghaeblog2.service;

import com.example.hanghaeblog2.dto.CommentRequestDto;
import com.example.hanghaeblog2.dto.CommentResponseDto;
import com.example.hanghaeblog2.dto.statusResponseDto;
import com.example.hanghaeblog2.entity.Comment;
import com.example.hanghaeblog2.entity.Member;
import com.example.hanghaeblog2.entity.Post;
import com.example.hanghaeblog2.entity.UserRole;
import com.example.hanghaeblog2.exception.customException.AuthorityException;
import com.example.hanghaeblog2.exception.customException.TokenException;
import com.example.hanghaeblog2.jwt.JwtUtil;
import com.example.hanghaeblog2.repository.CommentRepository;
import com.example.hanghaeblog2.repository.MemberRepository;
import com.example.hanghaeblog2.repository.PostRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;

    // 댓글 등록
    @Transactional
    public CommentResponseDto postComment(CommentRequestDto requestDto, HttpServletRequest request, Long id) throws TokenException {
        //토큰 검사
        Member member = checkTokenValidation(request);
        // 게시글 DB 유무 확인
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("등록된 게시글이 없습니다.")
        );
        // 등록
        Comment comment = new Comment(requestDto, post, member);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(HttpServletRequest request, CommentRequestDto requestDto, Long comment) {
        Comment checkedComment = commentValidation(request, comment);
        checkedComment.update(requestDto);
        return new CommentResponseDto(checkedComment);
    }

    // 댓글 삭제
    @Transactional
    public statusResponseDto deleteService(HttpServletRequest request, Long comment) {
        Comment checkedComment = commentValidation(request, comment);
        commentRepository.delete(checkedComment);
        return new statusResponseDto("댓글 삭제 성공", HttpStatus.OK);
    }

    // 토큰 유효성 / 아이디 유무 검사
    public Member checkTokenValidation(HttpServletRequest request) throws TokenException {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        Member member;
        // 토큰 유무
        if (token != null) {
            // 토큰 일치 검사
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new TokenException("Token Error");
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

    // 댓글 유효성 검사
    private Comment commentValidation(HttpServletRequest request, Long comment) {
        // 토큰 검사
        Member member = checkTokenValidation(request);
        // 댓글 유무 확인
        Comment findComment = commentRepository.findById(comment).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        // 권한 확인
        if(!(member.getRole() == UserRole.ADMIN || member.getId() == findComment.getMember().getId())) {
            throw new AuthorityException("권한이 없습니다.");
        }
        return findComment;
    }

}
