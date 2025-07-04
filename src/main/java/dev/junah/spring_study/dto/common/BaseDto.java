package dev.junah.spring_study.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "베이스 DTO")
public class BaseDto {
    @Schema(description = "ID", example = "495c417d-dc37-437b-8d34-64e176c16ccf")
    private String id;

    @Schema(description = "생성 시간", example = "2023-10-01T12:00:00Z")
    private String createdAt;

    @Schema(description = "마지막 수정 시간", example = "2023-10-01T12:00:00Z")
    private String updatedAt;
}
