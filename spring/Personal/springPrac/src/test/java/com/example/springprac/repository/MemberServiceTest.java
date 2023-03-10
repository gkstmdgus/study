package com.example.springprac.repository;

import com.example.springprac.domain.Grade;
import com.example.springprac.domain.Member;
import com.example.springprac.service.MemberService;
import com.example.springprac.service.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@DisplayName("MemberService 클래스의")
//public class MemberServiceTest {
//
//    private MemberService memberService = new MemberServiceImpl();
//
//    @DisplayName("join 메서드는")
//    @Nested
//    class Describe_join {
//        @DisplayName("만약 아이디기 중복되지 않은 멤버를 저장할 경우")
//        @Nested
//        class Context_with_saved_valid_member {
//            @DisplayName("회원 정보를 저장한다.")
//            @Test
//            void join() {
//                //given
//                Member memberA = new Member(1L, "memberA", Grade.VIP);
//
//                //when
//                memberService.join(memberA);
//                Member findMember = memberService.findMember(memberA.getId());
//
//                //then
//                assertThat(findMember).isEqualTo(findMember);
//            }
//        }
//    }
//}
