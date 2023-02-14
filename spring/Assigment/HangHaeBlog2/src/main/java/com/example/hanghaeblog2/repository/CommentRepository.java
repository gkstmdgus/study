package com.example.hanghaeblog2.repository;

import com.example.hanghaeblog2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
