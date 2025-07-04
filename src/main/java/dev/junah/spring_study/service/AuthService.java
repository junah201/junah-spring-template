package dev.junah.spring_study.service;

import dev.junah.spring_study.domain.User;
import dev.junah.spring_study.dto.auth.LoginReqDto;
import dev.junah.spring_study.dto.auth.LoginResDto;
import dev.junah.spring_study.dto.auth.SignupReqDto;
import dev.junah.spring_study.exception.auth.EmailAlreadyExistsException;
import dev.junah.spring_study.exception.auth.EmailNotFoundException;
import dev.junah.spring_study.exception.auth.InvalidPasswordException;
import dev.junah.spring_study.mapper.UserMapper;
import dev.junah.spring_study.security.JwtProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserService userService;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;

    public LoginResDto login(LoginReqDto loginReqDto) {
        User user = userService.findByEmail(loginReqDto.getEmail())
                .orElseThrow(EmailNotFoundException::new);

        if (!bCryptPasswordEncoder.matches(loginReqDto.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        String accessToken = jwtProvider.generateAccessToken(user.getId());
        String refreshToken = jwtProvider.generateRefreshToken(user.getId());

        return LoginResDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void signup(SignupReqDto signupReqDto) {
        userService.findByEmail(signupReqDto.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException();
                });

        signupReqDto.setPassword(bCryptPasswordEncoder.encode(signupReqDto.getPassword()));

        userService.save(userMapper.toEntity(signupReqDto));
    }
}
