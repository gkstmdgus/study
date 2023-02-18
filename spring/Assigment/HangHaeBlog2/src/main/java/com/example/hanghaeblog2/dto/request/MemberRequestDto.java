package com.example.hanghaeblog2.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {
    private String username;
    private String password;
    private boolean admin;
}
