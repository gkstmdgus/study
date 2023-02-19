package com.example.hanghaeblog2.entity.like;

import com.example.hanghaeblog2.entity.Member;
import com.example.hanghaeblog2.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PostLike {

    @Id
    @GeneratedValue
    @Column(name = "post_like_id")
    private Long postLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    private boolean likeNumber = true;

    public PostLike(Post post, Member member) {
        this.post = post;
        this.member = member;
    }

//    // 좋아요 상태 변경
//    public void changeLike() {
//        this.likeNumber = !likeNumber;
//    }
}
