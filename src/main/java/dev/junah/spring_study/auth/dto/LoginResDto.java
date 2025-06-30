package dev.junah.spring_study.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResDto {
    private String accessToken;
    private String refreshToken;
    private String userId;
}