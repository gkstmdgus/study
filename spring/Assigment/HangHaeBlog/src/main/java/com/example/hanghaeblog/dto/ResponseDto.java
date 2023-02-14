package com.example.hanghaeblog.dto;

import com.example.hanghaeblog.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Long id;
    private String author;
    private String title;
    private String content;

    public ResponseDto(Memo memo) {
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
        this.id = memo.getId();
        this.author = memo.getAuthor();
        this.title = memo.getTitle();
        this.content = memo.getContent();
    }

    @Getter
    public static class isSuccess {
        private boolean success;

        public isSuccess(boolean success) {
            this.success = success;
        }
    }

    @Getter
    public static class id {
        private Long id;

        public id(Long id) {
            this.id = id;
        }
    }
}
