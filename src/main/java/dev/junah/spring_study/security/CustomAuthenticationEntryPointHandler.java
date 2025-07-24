package dev.junah.spring_study.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.junah.spring_study.dto.common.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        // 만약 이 부분 응답을 수정하면, annotation/AdminOnly.java 및 annotation/LoginRequired.java 의
        // 예제도 같이 수정해야 합니다.

        Response<Void> res = Response.<Void>error(10401, "로그인이 필요합니다.");
        response.getWriter().write(objectMapper.writeValueAsString(res));
    }
}
