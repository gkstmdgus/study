package com.example.hanghaeblog2.exception;

import com.example.hanghaeblog2.dto.response.statusResponseDto;
import com.example.hanghaeblog2.exception.customException.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class exceptionHandler {
//    // Exception
//    @ExceptionHandler(value = Exception.class)
//    public statusResponseDto Exception(Exception e) {
//        System.out.println(e.getMessage());
//        return new statusResponseDto("Exception 발생.", HttpStatus.BAD_REQUEST);
//    }
//
//    // RuntimeException
//    @ExceptionHandler(value = RuntimeException.class)
//    public statusResponseDto RuntimeException(Exception e) {
//        System.out.println(e.getMessage());
//        return new statusResponseDto("Runtime Exception 발생", HttpStatus.BAD_REQUEST);
//    }
    // 토큰이 없어요  TokenException
    @ExceptionHandler({TokenException.class, SignatureException.class})
    public statusResponseDto TokenException(Exception e) {
        return new statusResponseDto("토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
    }

    // 권한이 없어요  AuthorityException
    @ExceptionHandler(value = AuthorityException.class)
    public statusResponseDto AuthorityException(Exception e) {
        return new statusResponseDto("작성자만 삭제/수정할 수 있습니다.", HttpStatus.BAD_REQUEST);
    }

    // 중복된 아이디에요  DuplicatedIdException
    @ExceptionHandler(value = DuplicatedIdException.class)
    public statusResponseDto DuplicatedIdException(Exception e) {
        return new statusResponseDto("중복된 username입니다.", HttpStatus.BAD_REQUEST);
    }

    // 회원을 찾을수 없어요  UnknownException
    @ExceptionHandler(value = UnknownException.class)
    public statusResponseDto UnknownException(Exception e) {
        return new statusResponseDto("회원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
    }

    // 등록된 자료가 없어요  NoPostException
    @ExceptionHandler(value = NoPostException.class)
    public statusResponseDto NoPostException(Exception e) {
        return new statusResponseDto("등록된 자료가 없습니다.", HttpStatus.BAD_REQUEST);
    }

    // 입력 값이 적합하지 않아요  InvalidValueException
    @ExceptionHandler(value = InvalidValueException.class)
    public statusResponseDto InvalidValueException(Exception e) {
        return new statusResponseDto("입력 값이 적합하지 않습니다.", HttpStatus.BAD_REQUEST);
    }

}
