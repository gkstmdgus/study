package com.example.hanghaeblog.dto;

import com.example.hanghaeblog.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private String password;

    public RequestDto(Memo memo) {
        this.id = memo.getId();
        this.author = memo.getAuthor();
        this.title = memo.getTitle();
        this.content = memo.getContent();
        this.password = memo.getPassword();
    }
}
