package com.example.hanghaeblog2.controller;

import com.example.hanghaeblog2.dto.MemberRequestDto;
import com.example.hanghaeblog2.dto.statusResponseDto;
import com.example.hanghaeblog2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberSerive;

    @PostMapping("/signup")
    public statusResponseDto signUp(@RequestBody MemberRequestDto memberRequestDto) {
        return memberSerive.signUp(memberRequestDto);
    }

    @PostMapping("/login")
    public statusResponseDto logIn(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) {
        return memberSerive.logIn(memberRequestDto, response);
    }
}
