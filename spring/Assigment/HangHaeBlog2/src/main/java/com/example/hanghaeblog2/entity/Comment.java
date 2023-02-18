package com.example.hanghaeblog2.entity;

import com.example.hanghaeblog2.dto.request.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends TimeStamped {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private String author;

    @Column
    private String content;

    public Comment(CommentRequestDto requestDto, Post post, Member member) {
        this.post = post;
        this.member = member;
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
    }

    public void update(CommentRequestDto requestDto) {
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
    }
}
