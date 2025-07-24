package dev.junah.spring_study.controller;

import dev.junah.spring_study.annotation.AdminOnly;
import dev.junah.spring_study.annotation.LoginRequired;
import dev.junah.spring_study.dto.common.Pagination;
import dev.junah.spring_study.dto.common.PaginationReqDto;
import dev.junah.spring_study.dto.common.Response;
import dev.junah.spring_study.dto.user.UserAdminUpdateDto;
import dev.junah.spring_study.dto.user.UserResDto;
import dev.junah.spring_study.dto.user.UserSelfUpdateDto;
import dev.junah.spring_study.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@LoginRequired
@Tag(name = "User", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @AdminOnly
    @GetMapping("")
    @Operation(summary = "모든 사용자 조회", description = "페이지네이션을 적용하여 모든 사용자를 조회합니다.")
    public Response<Pagination<UserResDto>> getUsers(PaginationReqDto paginationReqDto) {
        Pagination<UserResDto> users = userService.findAll(paginationReqDto.getPage(), paginationReqDto.getSize());

        return Response.ok(users);
    }

    @AdminOnly
    @GetMapping("/{id}")
    @Operation(summary = "사용자 조회", description = "ID로 특정 사용자를 조회합니다.")
    public Response<UserResDto> getUser(@PathVariable Long id) {
        UserResDto user = userService.getById(id);

        return Response.ok(user);
    }

    @AdminOnly
    @PatchMapping("/{id}")
    @Operation(summary = "사용자 정보 수정", description = "ID로 특정 사용자의 정보를 수정합니다.")
    public Response<UserResDto> updateUser(@PathVariable Long id,
            @RequestBody UserAdminUpdateDto userUpdateDto) {
        UserResDto updatedUser = userService.update(id, userUpdateDto);

        return Response.ok(updatedUser);
    }

    @GetMapping("me")
    @Operation(summary = "내 정보 조회", description = "로그인한 사용자의 정보를 조회합니다.")
    public Response<UserResDto> getMyInfo(@AuthenticationPrincipal Long userId) {
        System.out.println("UserController.getMyInfo: userId = " + userId);

        UserResDto user = userService.getById(userId);

        return Response.ok(user);
    }

    @PatchMapping("me")
    @Operation(summary = "내 정보 수정", description = "로그인한 사용자의 정보를 수정합니다.")
    public Response<UserResDto> updateMyInfo(@AuthenticationPrincipal Long userId,
            @RequestBody UserSelfUpdateDto userSelfUpdateDto) {
        UserResDto updatedUser = userService.update(userId, userSelfUpdateDto);

        return Response.ok(updatedUser);
    }
}
