package com.example.springprac.repository;

import com.example.springprac.domain.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
