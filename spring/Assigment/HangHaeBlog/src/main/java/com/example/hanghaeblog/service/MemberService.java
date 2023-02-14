package com.example.hanghaeblog.service;

import com.example.hanghaeblog.dto.MemberResponseDto;
import com.example.hanghaeblog.entity.Member;
import com.example.hanghaeblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long postMember(MemberResponseDto memberResponseDto) {
        Member member = new Member(memberResponseDto);
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public MemberResponseDto findMember(Long id, MemberResponseDto memberResponseDto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NullPointerException("회원 상세 조회 실패")
        );
        return new MemberResponseDto(member);
    }

    @Transactional
    public List<MemberResponseDto> findAllMember() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberDto = new ArrayList<>();
        for (Member member : members) {
            memberDto.add(new MemberResponseDto(member));
        }
        return memberDto;
    }
}