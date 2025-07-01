package dev.junah.spring_study.commom.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.junah.spring_study.commom.dto.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseCustomException.class)
    public Response<?> handleBaseCustomException(BaseCustomException e) {
        return Response.builder()
                .rqMethod("GlobalExceptionHandler.handleBaseCustomException")
                .rsCode(e.getErrorCode())
                .rsMsg(e.getErrorMessage())
                .build();
    }
}
