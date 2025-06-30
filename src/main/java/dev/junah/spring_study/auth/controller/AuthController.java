package dev.junah.spring_study.auth.controller;

import dev.junah.spring_study.auth.dto.LoginReqDto;
import dev.junah.spring_study.auth.dto.LoginResDto;
import dev.junah.spring_study.auth.dto.SignupReqDto;
import dev.junah.spring_study.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth/*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupReqDto signupReqDto) {
        authService.signup(signupReqDto);
    }

    @PostMapping("/login")
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) {
        return authService.login(loginReqDto);
    }
}
