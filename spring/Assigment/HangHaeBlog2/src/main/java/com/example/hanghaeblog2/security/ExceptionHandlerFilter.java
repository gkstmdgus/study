package com.example.hanghaeblog2.security;

import com.example.hanghaeblog2.dto.response.statusResponseDto;
import com.example.hanghaeblog2.exception.ExceptionRole;
import com.example.hanghaeblog2.exception.customException.UnknownException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@Component
@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        } catch (SecurityException | MalformedJwtException e) {
            log.info(ExceptionRole.SECURITY_EXCEPTION.getDesc());
            customExceptionHandler(response,ExceptionRole.SECURITY_EXCEPTION.getDesc(),HttpStatus.BAD_REQUEST);
        } catch (ExpiredJwtException e) {
            log.info(ExceptionRole.EXPIRED_JWT_EXCEPTION.getDesc());
            customExceptionHandler(response,ExceptionRole.EXPIRED_JWT_EXCEPTION.getDesc(),HttpStatus.BAD_REQUEST);
        } catch (UnsupportedJwtException e) {
            log.info(ExceptionRole.UNSUPPORTED_JWT_EXCEPTION.getDesc());
            customExceptionHandler(response,ExceptionRole.UNSUPPORTED_JWT_EXCEPTION.getDesc(),HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            log.info(ExceptionRole.ILLEGAL_ARGUMENT_EXCEPTION.getDesc());
            customExceptionHandler(response,ExceptionRole.ILLEGAL_ARGUMENT_EXCEPTION.getDesc(),HttpStatus.BAD_REQUEST);
        } catch (UnknownException e) {
            log.info(ExceptionRole.UNKNOWN_EXCEPTION.getDesc());
            customExceptionHandler(response,ExceptionRole.UNKNOWN_EXCEPTION.getDesc(), HttpStatus.BAD_REQUEST);
        }
        catch (SignatureException e) {
            log.info(ExceptionRole.EXCEPTION.getDesc());
            customExceptionHandler(response,ExceptionRole.EXCEPTION.getDesc(),HttpStatus.BAD_REQUEST);
        }
    }

    private void customExceptionHandler(HttpServletResponse response, String tokenError, HttpStatus value) {
        response.setStatus(value.value());
        response.setContentType("application/json; charset=UTF=8");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(new statusResponseDto(tokenError, value));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
