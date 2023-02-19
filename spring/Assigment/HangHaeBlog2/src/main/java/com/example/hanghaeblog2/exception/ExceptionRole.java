package com.example.hanghaeblog2.exception;

public enum ExceptionRole {
    SECURITY_EXCEPTION("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다."),
    EXPIRED_JWT_EXCEPTION("Expired JWT token, 만료된 JWT token 입니다."),
    UNSUPPORTED_JWT_EXCEPTION("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다."),
    ILLEGAL_ARGUMENT_EXCEPTION("JWT claims is empty, 잘못된 JWT 토큰 입니다."),
    UNKNOWN_EXCEPTION("회원을 찾을 수 없습니다."),
    EXCEPTION("Token Error");

    private String desc;

    ExceptionRole(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
