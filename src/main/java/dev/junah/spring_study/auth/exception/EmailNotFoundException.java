package dev.junah.spring_study.auth.exception;

import dev.junah.spring_study.commom.exception.BaseCustomException;

public class EmailNotFoundException extends BaseCustomException {
    public EmailNotFoundException() {
        super(10001, "존재하지 않는 이메일입니다.");
    }
}
