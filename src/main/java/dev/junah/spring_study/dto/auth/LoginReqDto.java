package dev.junah.spring_study.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "로그인 요청 DTO")
public class LoginReqDto {
    @Schema(description = "이메일", example = "example@gmail.com")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @Schema(description = "비밀번호")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
