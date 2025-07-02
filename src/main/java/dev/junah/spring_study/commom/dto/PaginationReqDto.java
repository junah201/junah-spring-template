package dev.junah.spring_study.commom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "페이징 요청 DTO")
public class PaginationReqDto {
    @Schema(description = "페이지 번호", example = "0")
    private int page = 0;

    @Schema(description = "페이지당 항목 수", example = "20")
    private int size = 20;
}
