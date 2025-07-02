package dev.junah.spring_study.users.exception;

import dev.junah.spring_study.commom.exception.BaseCustomException;

public class UserNotFoundException extends BaseCustomException {
    public UserNotFoundException() {
        super(10004, "존재하지 않는 사용자입니다.");
    }
}
