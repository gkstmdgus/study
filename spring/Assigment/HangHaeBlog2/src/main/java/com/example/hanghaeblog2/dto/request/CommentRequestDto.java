package com.example.hanghaeblog2.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String author;
    private String content;
}
