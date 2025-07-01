package dev.junah.spring_study.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "사용자 응답 DTO")
public class UserDto {
    @Schema(description = "사용자 ID", example = "1")
    private String id;

    @Schema(description = "사용자 이름", example = "username")
    private String username;

    @Schema(description = "사용자 이메일", example = "user@example.com")
    private String email;
}
