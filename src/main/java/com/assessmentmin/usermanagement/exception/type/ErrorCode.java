package com.assessmentmin.usermanagement.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "유저를 찾을 수 없습니다."),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다."),
    UNSUPPORTED_USER_ROLE(HttpStatus.BAD_REQUEST, "해당 유저 권한을 지원하지 않습니다"),
    LOGIN_FAILED(HttpStatus.BAD_REQUEST, "로그인에 실패했습니다."),
    LOGIN_NEEDED(HttpStatus.BAD_REQUEST, "로그인 해 주세요."),
    INVALID_LOGIN_INFO(HttpStatus.BAD_REQUEST,"아이디 또는 비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

}
