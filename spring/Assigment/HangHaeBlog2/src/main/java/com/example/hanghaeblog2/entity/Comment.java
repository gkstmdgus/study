package com.example.hanghaeblog2.entity;

import com.example.hanghaeblog2.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column
    private String author;

    @Column
    private String content;

    public Comment(CommentRequestDto requestDto, Post post) {
        this.post = post;
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
    }
}
