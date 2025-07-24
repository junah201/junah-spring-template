package dev.junah.spring_study.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Schema(description = "페이징 요청 DTO")
public class PaginationReqDto {
    @Schema(description = "페이지 번호", example = "0")
    @Builder.Default
    private int page = 0;

    @Schema(description = "페이지당 항목 수", example = "20")
    @Builder.Default
    private int size = 20;
}
