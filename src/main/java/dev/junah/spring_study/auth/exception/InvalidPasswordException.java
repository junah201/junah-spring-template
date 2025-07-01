package dev.junah.spring_study.auth.exception;

import dev.junah.spring_study.commom.exception.BaseCustomException;

public class InvalidPasswordException extends BaseCustomException {
    public InvalidPasswordException() {
        super(10002, "잘못된 비밀번호입니다.");
    }
}
