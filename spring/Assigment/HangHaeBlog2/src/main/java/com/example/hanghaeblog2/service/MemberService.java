package com.example.hanghaeblog2.service;

import com.example.hanghaeblog2.dto.MemberRequestDto;
import com.example.hanghaeblog2.dto.statusResponseDto;
import com.example.hanghaeblog2.entity.Member;
import com.example.hanghaeblog2.exception.customException.DuplicatedIdException;
import com.example.hanghaeblog2.exception.customException.InvalidValueException;
import com.example.hanghaeblog2.exception.customException.UnknownException;
import com.example.hanghaeblog2.jwt.JwtUtil;
import com.example.hanghaeblog2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public statusResponseDto signUp(MemberRequestDto memberRequestDto) {
        String username = memberRequestDto.getUsername();
        String password = memberRequestDto.getPassword();
        // 아이디 중복
        if (memberRepository.count() != 0 && memberRepository.findByUsername(username).isPresent()) {
            throw new DuplicatedIdException("중복된 아이디가 존재합니다.");
        }
        // 아이디 검사
        if(4 > username.length() || username.length() > 10 || username.replaceAll("[0-9a-z]","").length() != 0) {
            throw new InvalidValueException("아이디가 적합하지 않습니다.");
        }
        // 비밀번호 검사
        if(8 > password.length() || password.length() > 15 || password.replaceAll("[0-9a-zA-Z]","").length() != 0)
            throw new InvalidValueException("비밀번호가 적합하지 않습니다.");
        Member member = new Member(memberRequestDto);
        memberRepository.save(member);
        return new statusResponseDto("회원가입 성공", HttpStatus.OK);
    }

    @Transactional
    public statusResponseDto logIn(MemberRequestDto memberRequestDto, HttpServletResponse response) {
        // 아이디 유효성
        Member member =  memberRepository.findByUsername(memberRequestDto.getUsername()).orElseThrow(
                () -> new UnknownException("아이디가 존재하지 않습니다.")
        );
        // 비밀번호 일치 여부
        if(!member.getPassword().equals(memberRequestDto.getPassword()))
            throw new UnknownException("아이디와 비밀번호가 일치하지 않습니다.");
        // 토큰 발급 , 헤더에 입력
        response.addHeader(jwtUtil.AUTHORIZATION_HEADER,jwtUtil.createToken(member.getUsername()));
        // ResponseDto 반환
        return new statusResponseDto("로그인 성공", HttpStatus.OK);
    }

    public void checkIdValidation(String id) {
        // 길이 검사 && 문자 검사
        if(4 > id.length() || id.length() > 10 || id.replaceAll("[0-9a-z]","").length() != 0)
            throw new InvalidValueException("아이디가 적합하지 않습니다.");
    }
}
