package com.example.hanghaeblog2.dto.response;

import com.example.hanghaeblog2.entity.Comment;
import com.example.hanghaeblog2.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList = new ArrayList<>();
    private int postLikeNumber;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getMember().getUsername();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.postLikeNumber = post.getPostLikes().size();
    }

    public PostResponseDto(Post post, List<Comment> list) {
        this(post);
        for (Comment comment : list) {
            this.commentList.add(new CommentResponseDto(comment));
        }
    }
}
