package dev.junah.spring_study.exception.auth;

import dev.junah.spring_study.exception.BaseCustomException;

public class EmailAlreadyExistsException extends BaseCustomException {
    public EmailAlreadyExistsException() {
        super(10003, "이미 존재하는 이메일입니다.");
    }
}
