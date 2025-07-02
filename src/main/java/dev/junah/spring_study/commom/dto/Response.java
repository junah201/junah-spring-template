package dev.junah.spring_study.commom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    @Schema(description = "수행 메서드 명")
    private String rqMethod;

    @Schema(description = "결과 코드", example = "0")
    @Builder.Default
    private Integer rsCode = 0;

    @Schema(description = "결과 메시지", example = "성공")
    @Builder.Default
    private String rsMsg = "성공";

    @Schema(description = "결과 데이터")
    @Builder.Default
    private T rsData = null;

    public static <T> Response<T> ok(String rqMethod, T data) {
        return Response.<T>builder()
                .rqMethod(rqMethod)
                .rsCode(0)
                .rsMsg("성공")
                .rsData(data)
                .build();
    }

    public static <T> Response<T> error(String rqMethod, Integer errorCode, String errorMessage) {
        return Response.<T>builder()
                .rqMethod(rqMethod)
                .rsCode(errorCode)
                .rsMsg(errorMessage)
                .build();
    }
}
