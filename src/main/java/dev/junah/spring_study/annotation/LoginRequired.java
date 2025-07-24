package dev.junah.spring_study.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("isAuthenticated()")
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
})
public @interface LoginRequired {
}
