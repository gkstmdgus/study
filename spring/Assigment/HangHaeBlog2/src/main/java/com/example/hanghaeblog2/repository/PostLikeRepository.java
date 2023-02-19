package com.example.hanghaeblog2.repository;

import com.example.hanghaeblog2.entity.like.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
//    List<PostLike> findByMember_IdAndPost_Id(Long memberId,Long postId);
    PostLike findByMember_IdAndPost_Id(Long memberId,Long postId);
}
