package com.example.hanghaeblog2.security;

import com.example.hanghaeblog2.entity.Member;
import com.example.hanghaeblog2.exception.customException.UnknownException;
import com.example.hanghaeblog2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                        .orElseThrow(UnknownException::new);
        return new UserDetailsImpl(member, member.getUsername());
    }
}
