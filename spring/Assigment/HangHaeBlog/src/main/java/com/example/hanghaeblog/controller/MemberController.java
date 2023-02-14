package com.example.hanghaeblog.controller;


import com.example.hanghaeblog.dto.MemberResponseDto;
import com.example.hanghaeblog.entity.Member;
import com.example.hanghaeblog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public Long postMember(@RequestBody MemberResponseDto memberResponseDto){
        return memberService.postMember(memberResponseDto);
    }

    /**
     * 한 회원의 userId가 주었을때 회원 정보를 조회하는 API
     * @param
     */
    @GetMapping("/member/{id}")
    public MemberResponseDto getMemberInfo(@PathVariable Long id, @RequestBody MemberResponseDto memberResponseDto) {
        return memberService.findMember(id,memberResponseDto);
    }

    /**
     * 회원의 전체 목록을 조회하는 API
     */
    @GetMapping("/member")
    public List<MemberResponseDto> getMemberList() {
        return memberService.findAllMember();
    }

}