package com.example.hanghaeblog2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberRequestDto {
    private String username;
    private String password;
    private boolean admin;
}
