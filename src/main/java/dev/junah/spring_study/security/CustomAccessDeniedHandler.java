package dev.junah.spring_study.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.junah.spring_study.dto.common.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        // 만약 이 부분 응답을 수정하면, annotation/AdminOnly.java 및 annotation/LoginRequired.java 의
        // 예제도 같이 수정해야 합니다.

        Response<Void> res = Response.<Void>error(10403, "접근이 거부되었습니다.");
        response.getWriter().write(objectMapper.writeValueAsString(res));
    }
}
