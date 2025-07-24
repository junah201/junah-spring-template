package dev.junah.spring_study.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "jwtAuth")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "401", description = "로그인이 필요합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                {
                  "code": 10401,
                  "message": "로그인이 필요합니다.",
                  "data": null
                }
                """))),
        @ApiResponse(responseCode = "403", description = "접근이 거부되었습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                {
                  "code": 10403,
                  "message": "접근이 거부되었습니다.",
                  "data": null
                }
                """)))
})
public @interface AdminOnly {
}
