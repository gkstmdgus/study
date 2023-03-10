package com.example.hanghaeblog2.jwt;

import com.example.hanghaeblog2.dto.response.statusResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);

        if (token != null) {
            // **  ExceptionHandlerFilter에서 한 번에 처리 **
//            if (!jwtUtil.validateToken(token)) {
//                jwtExceptionHandler(response, "Token Error", HttpStatus.UNAUTHORIZED);
//                return;
//            }
            Claims info = jwtUtil.getUserInfoFromToken(token);
            setAuthentication(info.getSubject());
        }
        filterChain.doFilter(request, response);
    }

    // securityContextHolder에 저장
    private void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtUtil.createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    /**
     *  ExceptionHandlerFilter에서 한 번에 처리
     */

//    private void jwtExceptionHandler(HttpServletResponse response, String tokenError, HttpStatus value) {
//        response.setStatus(value.value());
//        response.setContentType("application/json");
//        try {
//            String json = new ObjectMapper().writeValueAsString(new statusResponseDto(tokenError, value));
//            response.getWriter().write(json);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }
}
