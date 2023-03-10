package com.example.hanghaeblog2.dto.response;

import com.example.hanghaeblog2.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private Long comment;
    private String author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int CommentLikeNumber;

    public CommentResponseDto(Comment comment) {
        this.comment = comment.getId();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.CommentLikeNumber = comment.getCommentLikes().size();
    }
}
