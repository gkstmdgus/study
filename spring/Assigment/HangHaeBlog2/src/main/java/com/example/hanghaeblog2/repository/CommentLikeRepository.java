package com.example.hanghaeblog2.repository;

import com.example.hanghaeblog2.entity.like.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    CommentLike findByMember_IdAndComment_Id(Long memberId, Long CommentId);
}
