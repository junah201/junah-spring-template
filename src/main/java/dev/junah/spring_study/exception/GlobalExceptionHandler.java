package dev.junah.spring_study.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.junah.spring_study.dto.common.Response;

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
