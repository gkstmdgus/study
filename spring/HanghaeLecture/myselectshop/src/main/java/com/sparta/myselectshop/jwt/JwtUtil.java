package com.sparta.myselectshop.jwt;


import com.sparta.myselectshop.entity.UserRoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
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
    private static final long TOKEN_TIME = 60 * 60 * 1000L;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    // 토큰 생성

    @PostConstruct
    // 의존성 주입 후 초기화를 수행하는 메서드
    public void init() {
        // 주어진 시크릿 키 디코드?
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // 토큰 생성
    public String createToken(String username, UserRoleEnum role) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        // 토큰 제목 (sub)
                        .setSubject(username)
                        // 비공개 클레임 설정 (key-value)
                        .claim(AUTHORIZATION_KEY, role)
                        // 만료 시간 (exp)
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                        // 발급 시간 (iat)
                        .setIssuedAt(date)
                        // 어떤 알고리즘과 키값으로 sign할지 (Header와 Payload를 인코딩 + 해싱 시)
                        .signWith(key, signatureAlgorithm)
                        // JWT 토큰 만들기
                        .compact();
    }

    // 토큰 확인

    // header 토큰을 가져오기
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        // bearerToken이 null아니고 길이 0 아니고 공백 아니고 / BEARER_PREFIX(Bearer )로 시작하는지
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    // JWT 검증을 위한 Secret Key 또는 public Key 등록
                    .setSigningKey(key)
                    // 쓰레드에 안전한 JwtPaser를 리턴하기 위해? JwtParserBuilder -> JwtParser
                    .build()
                    // setSigningKey(key)에서 등록한 key를 가지고 유효한 토큰인지 검증
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

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().
                setSigningKey(key).
                build().
                parseClaimsJws(token).
                // Returns the JWT body
                getBody();
    }

}