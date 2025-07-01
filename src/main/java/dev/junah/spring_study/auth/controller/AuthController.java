package dev.junah.spring_study.auth.controller;

import dev.junah.spring_study.auth.dto.LoginReqDto;
import dev.junah.spring_study.auth.dto.LoginResDto;
import dev.junah.spring_study.auth.dto.SignupReqDto;
import dev.junah.spring_study.auth.service.AuthService;
import dev.junah.spring_study.commom.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    public Response<Void> signup(@RequestBody @Valid SignupReqDto signupReqDto) {
        authService.signup(signupReqDto);
        return Response.<Void>builder()
                .rqMethod("POST /auth/signup")
                .build();
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자가 로그인하여 토큰을 발급받습니다.")
    public Response<LoginResDto> login(@RequestBody LoginReqDto loginReqDto) {
        LoginResDto loginResDto = authService.login(loginReqDto);
        return Response.<LoginResDto>builder()
                .rqMethod("POST /auth/login")
                .rsData(loginResDto)
                .build();
    }
}
