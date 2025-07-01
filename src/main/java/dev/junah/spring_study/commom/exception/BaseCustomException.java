package dev.junah.spring_study.commom.exception;

import lombok.Getter;

@Getter
public class BaseCustomException extends RuntimeException {
    private final int errorCode;
    private final String errorMessage;

    protected BaseCustomException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
