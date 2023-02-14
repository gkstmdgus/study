package com.example.hanghaeblog2.dto;

import com.example.hanghaeblog2.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private Long comment;
    private String author;
    private String content;

    public CommentResponseDto(Comment comment) {
        this.comment = comment.getComment();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
    }
}
