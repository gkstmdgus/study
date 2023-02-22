package com.example.hanghaeblog2.dto.fetch;

import com.example.hanghaeblog2.dto.fetch.FetchCommentsDto;
import com.example.hanghaeblog2.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class getAllPostDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    // dto로 수정 필요
    private List<FetchCommentsDto> commentList = new ArrayList<>();
    private int postLikenumber;

    public getAllPostDto(Post post) {
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();
        username = post.getMember().getUsername();
        postLikenumber = post.getPostLikes().size();    // 지연로딩
        commentList = post.getComments().stream().map(FetchCommentsDto::new).collect(Collectors.toList());
    }
}
