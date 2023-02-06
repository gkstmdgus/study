package com.example.springprac.service;

import com.example.springprac.domain.Member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}