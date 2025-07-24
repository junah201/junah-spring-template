package dev.junah.spring_study.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회원가입 요청 DTO")
public class SignupReqDto {
    @Schema(description = "이메일", format = "email")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @Schema(description = "비밀번호", format = "password")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @Schema(description = "사용자 이름", example = "홍길동")
    @NotBlank(message = "사용자 이름은 필수 입력 값입니다.")
    private String username;
}
