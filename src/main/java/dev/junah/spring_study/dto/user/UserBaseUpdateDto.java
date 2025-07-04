package dev.junah.spring_study.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "사용자 정보 업데이트 요청 DTO")
public class UserBaseUpdateDto {
    @Schema(description = "사용자 이름", example = "홍길동")
    private String username;

    @Schema(description = "이메일", example = "honggildong@example.com")
    private String email;
}
