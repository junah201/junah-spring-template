package dev.junah.spring_study.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    @Schema(description = "결과 코드", example = "0")
    @Builder.Default
    private Integer code = 0;

    @Schema(description = "결과 메시지", example = "성공")
    @Builder.Default
    private String message = "성공";

    @Schema(description = "결과 데이터")
    @Builder.Default
    private T data = null;

    public static <T> Response<T> ok(T data) {
        return Response.<T>builder()
                .code(0)
                .message("성공")
                .data(data)
                .build();
    }

    public static <T> Response<T> error(Integer errorCode, String errorMessage) {
        return Response.<T>builder()
                .code(errorCode)
                .message(errorMessage)
                .build();
    }
}
