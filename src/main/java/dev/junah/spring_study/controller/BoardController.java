package dev.junah.spring_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Board", description = "게시판 관련 API")
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
    
}
