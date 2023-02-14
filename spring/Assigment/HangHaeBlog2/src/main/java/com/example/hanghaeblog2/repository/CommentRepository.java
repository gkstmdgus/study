package com.example.hanghaeblog2.repository;

import com.example.hanghaeblog2.entity.Comment;
import com.example.hanghaeblog2.entity.Member;
import com.example.hanghaeblog2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentByPostOrderByCreatedAtDesc(Post post);
}
