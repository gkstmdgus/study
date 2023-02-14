package com.example.hanghaeblog2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class statusResponseDto {
    private String msg;
    private int statusCode;

    public statusResponseDto(String msg, HttpStatus statusCode) {
        this.msg = msg;
        this.statusCode = statusCode.value();
    }
}

