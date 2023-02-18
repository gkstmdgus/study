package com.example.hanghaeblog2.jwt;

import com.example.hanghaeblog2.entity.Member;
import com.example.hanghaeblog2.exception.customException.TokenException;
import com.example.hanghaeblog2.exception.customException.UnknownException;
import com.example.hanghaeblog2.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_KEY = "auth";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final long TOKEN_TIME = 60 * 60 * 1000L * 48;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // 토큰 생성
    public String createToken (String username) {
        Date date = new Date();
        return BEARER_PREFIX + Jwts.builder().
                setSubject(username).
                setExpiration(new Date(date.getTime() + TOKEN_TIME)).
                setIssuedAt(date).
                signWith(signatureAlgorithm,key).
                compact();
    }


    // 토큰 해체
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // 토큰 유효성 / 아이디 유무 검사
    public Member checkTokenValidation(HttpServletRequest request) {
        String token = this.resolveToken(request);
        Claims claims;
        Member member;
        // 토큰 유무
        if (token != null) {
            // 토큰 일치 검사
            if (this.validateToken(token)) {
                claims = this.getUserInfoFromToken(token);
            } else {
                throw new TokenException("Token Error");
            }
            // 토큰으로 아이디 가져오기
            member = memberRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new UnknownException("아이디가 존재하지 않습니다.")
            );
        } else {
            return null;
        }
        return member;
    }
}
