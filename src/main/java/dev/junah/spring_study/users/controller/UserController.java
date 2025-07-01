package dev.junah.spring_study.users.controller;

import dev.junah.spring_study.commom.dto.Pagination;
import dev.junah.spring_study.commom.dto.Response;
import dev.junah.spring_study.users.dto.UserDto;
import dev.junah.spring_study.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/*")
public class UserController {
    private final UserService userService;

    // @GetMapping("/all")
    // public Response<Pagination<UserDto>> getAllUsers() {
    // }

}
