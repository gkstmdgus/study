package com.example.springprac.service;

import com.example.springprac.domain.Member;
import com.example.springprac.repository.MemberRepository;
import com.example.springprac.repository.MemoryMemberRepository;
import com.example.springprac.service.MemberService;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}