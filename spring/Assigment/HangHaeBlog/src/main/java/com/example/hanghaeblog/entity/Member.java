package com.example.hanghaeblog.entity;

import com.example.hanghaeblog.dto.MemberResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String pw;

    public Member(MemberResponseDto memberResponseDto) {
        this.name = memberResponseDto.getName();
        this.email = memberResponseDto.getEmail();
        this.pw = memberResponseDto.getPw();
    }
}
