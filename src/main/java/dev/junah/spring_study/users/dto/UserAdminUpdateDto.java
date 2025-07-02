package dev.junah.spring_study.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "사용자 정보 업데이트 요청 DTO")
public class UserAdminUpdateDto extends UserSelfUpdateDto {
    @Schema(description = "권한", example = "1")
    private int permission;
}
