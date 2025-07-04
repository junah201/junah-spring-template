package dev.junah.spring_study.dto.user;

import dev.junah.spring_study.dto.common.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "사용자 응답 DTO")
public class UserResDto extends BaseDto {
    @Schema(description = "사용자 이름", example = "username")
    private String username;

    @Schema(description = "사용자 이메일", example = "user@example.com")
    private String email;

    @Schema(description = "사용자 권한", example = "0")
    private int permission;
}
