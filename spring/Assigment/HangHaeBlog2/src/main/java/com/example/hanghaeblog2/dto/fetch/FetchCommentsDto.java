package com.example.hanghaeblog2.dto.fetch;

import com.example.hanghaeblog2.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FetchCommentsDto {

    private Long commentId;
    private String author;
    private String content;
    private int commentLikes;

    public FetchCommentsDto(Comment comment) {
        commentId = comment.getId();
        author = comment.getAuthor();
        content = comment.getContent();
        commentLikes = comment.getCommentLikes().size();
    }
}
