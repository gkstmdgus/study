package com.example.hanghaeblog.dto;

import com.example.hanghaeblog.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RespDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Long id;
    private String author;
    private String title;
    private String content;

    public RespDto() {
    }

    public RespDto(Memo memo) {
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
        this.id = memo.getId();
        this.author = memo.getAuthor();
        this.title = memo.getTitle();
        this.content = memo.getContent();
    }
}
