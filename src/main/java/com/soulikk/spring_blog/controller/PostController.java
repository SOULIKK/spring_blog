package com.soulikk.spring_blog.controller;

import com.soulikk.spring_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/post")
@Log4j2
@RequiredArgsConstructor
public class PostController {
    
    private final PostService postService;

    @GetMapping("/list")
    public void getList() {

    }
}