package dev.junah.spring_study.security;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.info("[CustomAuthenticationEntryPointHandler] :: {}", authException.getMessage());
        log.info("[CustomAuthenticationEntryPointHandler] :: {}", request.getRequestURL());
        log.info("[CustomAuthenticationEntryPointHandler] :: 토근 정보가 만료되었거나 존재하지 않음");

        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        JsonObject returnJson = new JsonObject();
        returnJson.addProperty("errorCode", "ACCESS_DENIED");
        returnJson.addProperty("errorMsg", "접근이 거부되었습니다."); // TODO: ApiExceptionEnum을 만들어서 관리하기

        PrintWriter out = response.getWriter();
        out.print(returnJson);
    }
}
