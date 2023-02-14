package com.example.hanghaeblog.repository;

import com.example.hanghaeblog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository <Member, Long> {
}
