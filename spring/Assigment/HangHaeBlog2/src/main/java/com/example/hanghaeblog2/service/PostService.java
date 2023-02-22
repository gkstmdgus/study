package com.example.hanghaeblog2.service;

import com.example.hanghaeblog2.dto.fetch.GetPostDto;
import com.example.hanghaeblog2.dto.response.statusResponseDto;
import com.example.hanghaeblog2.dto.request.PostRequestDto;
import com.example.hanghaeblog2.dto.response.PostResponseDto;
import com.example.hanghaeblog2.entity.*;
import com.example.hanghaeblog2.entity.like.PostLike;
import com.example.hanghaeblog2.exception.customException.AuthorityException;
import com.example.hanghaeblog2.exception.customException.NoPostException;
import com.example.hanghaeblog2.exception.customException.UnknownException;
import com.example.hanghaeblog2.repository.CommentRepository;
import com.example.hanghaeblog2.repository.MemberRepository;
import com.example.hanghaeblog2.repository.PostLikeRepository;
import com.example.hanghaeblog2.repository.PostRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.hanghaeblog2.entity.QMember.*;
import static com.example.hanghaeblog2.entity.QPost.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final EntityManager em;
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
    public List<GetPostDto> getAllPosts() {
//        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
//        List<PostResponseDto> postDto = new ArrayList<>();
//        for (Post post : posts) {
//            postDto.add(new PostResponseDto(post, commentRepository.findCommentByPostOrderByCreatedAtDesc(post)));
//        }
//        return postDto;
        return getAllPostsFetch();
    }

    /**
     * 모든 게시글 조회 페치조인 적용해보기 (순수 JPA)
     */
    public List<GetPostDto> getAllPostsFetch() {
        List<Post> fetchList = em.createQuery(
                "select p" +
                        " from Post p" +
                        " join fetch p.member m", Post.class
        ).getResultList();
        List<GetPostDto> result = fetchList.stream().map(GetPostDto::new).collect(Collectors.toList());
        return result;
    }

    // 특정 게시글 조회
    @Transactional
    public ResponseEntity<GetPostDto> getPost(Long id) {
//        Post post = checkIdHasPost(id);
//        return ResponseEntity.ok(new PostResponseDto(post, post.getComments()));
        return ResponseEntity.ok(getPostQuery(id));
    }

    /**
     * QueryDSL을 사용해서 특정 게시물 조회하기
     */
    public GetPostDto getPostQuery(Long id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        Post resultPost = queryFactory
                .selectFrom(post)
                .join(post.member, member).fetchJoin()
                .where(post.id.eq(id))
                .fetchOne();
        // null 이면 없는 거임 예외 던지기
        if (resultPost == null) {
            throw new NoPostException("포스트 없음");
        }
        GetPostDto result = new GetPostDto(resultPost);
        return result;
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
