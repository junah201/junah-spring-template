package dev.junah.spring_study.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Schema(description = "베이스 DTO")
public class BaseDto {
    @Schema(description = "ID", example = "1")
    private String id;

    @Schema(description = "생성 시간", format = "date-time")
    private String createdAt;

    @Schema(description = "마지막 수정 시간", format = "date-time")
    private String updatedAt;
}
