package dev.junah.spring_study.auth.service;

import dev.junah.spring_study.auth.dto.LoginReqDto;
import dev.junah.spring_study.auth.dto.LoginResDto;
import dev.junah.spring_study.auth.dto.SignupReqDto;
import dev.junah.spring_study.auth.exception.EmailAlreadyExistsException;
import dev.junah.spring_study.auth.exception.EmailNotFoundException;
import dev.junah.spring_study.auth.exception.InvalidPasswordException;
import dev.junah.spring_study.commom.dto.Response;
import dev.junah.spring_study.security.JwtProvider;
import dev.junah.spring_study.users.domain.User;
import dev.junah.spring_study.users.service.UserService;
import lombok.RequiredArgsConstructor;

import java.security.InvalidParameterException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;
    private final ModelMapper modelMapper;

    public LoginResDto login(LoginReqDto loginReqDto) {
        User user = userService.findUserByEmail(loginReqDto.getEmail())
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
        userService.findUserByEmail(signupReqDto.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException();
                });

        signupReqDto.setPassword(bCryptPasswordEncoder.encode(signupReqDto.getPassword()));
        User user = modelMapper.map(signupReqDto, User.class);
        userService.save(user);
    }
}
