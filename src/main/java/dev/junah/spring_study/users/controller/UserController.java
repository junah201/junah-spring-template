package dev.junah.spring_study.users.controller;

import dev.junah.spring_study.commom.dto.Pagination;
import dev.junah.spring_study.commom.dto.PaginationReqDto;
import dev.junah.spring_study.commom.dto.Response;
import dev.junah.spring_study.users.dto.UserAdminUpdateDto;
import dev.junah.spring_study.users.dto.UserResDto;
import dev.junah.spring_study.users.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "User", description = "사용자 관련 API")
@Controller
@RequiredArgsConstructor
@RequestMapping("/users/*")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
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
    public Response<UserResDto> getUser(@PathVariable Long id) {
        UserResDto user = userService.getById(id);

        return Response.ok("GET /users/" + id, user);
    }

    @PatchMapping("/{id}")
    public Response<UserResDto> updateUser(@PathVariable Long id, UserAdminUpdateDto userUpdateDto) {
        UserResDto updatedUser = userService.update(id, userUpdateDto);

        return Response.ok("PATCH /users/" + id, updatedUser);
    }
}