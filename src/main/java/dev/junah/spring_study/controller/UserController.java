package dev.junah.spring_study.controller;

import dev.junah.spring_study.dto.common.Pagination;
import dev.junah.spring_study.dto.common.PaginationReqDto;
import dev.junah.spring_study.dto.common.Response;
import dev.junah.spring_study.dto.user.UserAdminUpdateDto;
import dev.junah.spring_study.dto.user.UserResDto;
import dev.junah.spring_study.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/*")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    @Operation(summary = "모든 사용자 조회", description = "페이지네이션을 적용하여 모든 사용자를 조회합니다.")
    public Response<Pagination<UserResDto>> getUsers(PaginationReqDto paginationReqDto) {
        List<UserResDto> users = userService.findAll(paginationReqDto.getPage(), paginationReqDto.getSize());

        return Response.ok(
                "GET /users/all",
                Pagination.<UserResDto>builder()
                        .nodes(users)
                        .page(paginationReqDto.getPage())
                        .size(paginationReqDto.getSize())
                        .build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "사용자 조회", description = "ID로 특정 사용자를 조회합니다.")
    public Response<UserResDto> getUser(@PathVariable String id) {
        UserResDto user = userService.getById(id);

        return Response.ok("GET /users/" + id, user);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "사용자 정보 수정", description = "ID로 특정 사용자의 정보를 수정합니다.")
    public Response<UserResDto> updateUser(@PathVariable String id, UserAdminUpdateDto userUpdateDto) {
        UserResDto updatedUser = userService.update(id, userUpdateDto);

        return Response.ok("PATCH /users/" + id, updatedUser);
    }
}