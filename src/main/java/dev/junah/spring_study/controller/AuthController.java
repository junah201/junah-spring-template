package dev.junah.spring_study.controller;

import dev.junah.spring_study.dto.auth.LoginReqDto;
import dev.junah.spring_study.dto.auth.LoginResDto;
import dev.junah.spring_study.dto.auth.SignupReqDto;
import dev.junah.spring_study.dto.common.Response;
import dev.junah.spring_study.service.AuthService;
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
        return Response.<Void>ok(null);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자가 로그인하여 토큰을 발급받습니다.")
    public Response<LoginResDto> login(@RequestBody LoginReqDto loginReqDto) {
        LoginResDto loginResDto = authService.login(loginReqDto);
        return Response.ok(loginResDto);
    }
}
