package dev.junah.spring_study.dto.common;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "페이징 처리된 결과 DTO")
public class Pagination<T> {
    @Schema(description = "현재 페이지 번호", example = "1")
    private int page;

    @Schema(description = "페이지당 항목 수", example = "10")
    private int size;

    @Schema(description = "페이지의 데이터 목록")
    private List<T> nodes;
}
