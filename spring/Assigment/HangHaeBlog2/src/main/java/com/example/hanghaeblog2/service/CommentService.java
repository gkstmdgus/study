package com.example.hanghaeblog2.service;

import com.example.hanghaeblog2.dto.request.CommentRequestDto;
import com.example.hanghaeblog2.dto.response.CommentResponseDto;
import com.example.hanghaeblog2.dto.response.statusResponseDto;
import com.example.hanghaeblog2.entity.Comment;
import com.example.hanghaeblog2.entity.Member;
import com.example.hanghaeblog2.entity.Post;
import com.example.hanghaeblog2.entity.UserRole;
import com.example.hanghaeblog2.exception.customException.AuthorityException;
import com.example.hanghaeblog2.exception.customException.NoPostException;
import com.example.hanghaeblog2.exception.customException.TokenException;
import com.example.hanghaeblog2.exception.customException.UnknownException;
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

    //== 비즈니스 로직 ==//

    // 댓글 등록
    @Transactional
    public CommentResponseDto postComment(CommentRequestDto requestDto, HttpServletRequest request, Long id) throws TokenException {
        //토큰 검사
        Member member = jwtUtil.checkTokenValidation(request);
        // 게시글 DB 유무 확인
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NoPostException("등록된 게시글이 없습니다.")
        );
        // 등록
        Comment comment = new Comment(requestDto, post, member);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(HttpServletRequest request, CommentRequestDto requestDto, Long comment) {
        // 댓글 유효성 검사
        Comment checkedComment = commentValidation(request, comment);
        // 댓글 업데이트
        checkedComment.update(requestDto);
        return new CommentResponseDto(checkedComment);
    }

    // 댓글 삭제
    @Transactional
    public statusResponseDto deleteService(HttpServletRequest request, Long comment) {
        // 댓글 유효성 검사
        Comment checkedComment = commentValidation(request, comment);
        // 댓글 업데이트
        commentRepository.delete(checkedComment);
        return new statusResponseDto("댓글 삭제 성공", HttpStatus.OK);
    }

    //== 반복 로직 ==/

    // 댓글 유효성 검사
    private Comment commentValidation(HttpServletRequest request, Long comment) {
        // 토큰 검사
        Member member = jwtUtil.checkTokenValidation(request);
        // 댓글 유무 확인
        Comment findComment = commentRepository.findById(comment).orElseThrow(
                () -> new NoPostException("댓글이 존재하지 않습니다.")
        );
        // 권한 확인
        if(!(member.getRole() == UserRole.ADMIN || member.getId() == findComment.getMember().getId())) {
            throw new AuthorityException("권한이 없습니다.");
        }
        return findComment;
    }

}
