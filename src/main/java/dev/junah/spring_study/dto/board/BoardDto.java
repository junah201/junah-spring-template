package dev.junah.spring_study.dto.board;

import dev.junah.spring_study.dto.common.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Schema(description = "게시판 DTO")
public class BoardDto extends BaseDto {
    @Schema(description = "게시판 이름", example = "공지사항")
    private String name;

    @Schema(description = "게시판 설명", example = "공지사항 게시판입니다.")
    private String description;
}
