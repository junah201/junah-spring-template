package dev.junah.spring_study.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Schema(description = "사용자 정보 업데이트 요청 DTO")
public class UserSelfUpdateDto extends UserBaseUpdateDto {
}
