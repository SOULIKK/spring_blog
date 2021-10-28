package com.soulikk.spring_blog.controller;


import com.soulikk.spring_blog.dto.PageRequestDto;
import com.soulikk.spring_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@Log4j2
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String getPostList(PageRequestDto pageableRequestDto, Model model) {
        model.addAttribute("posts", postService.getList(pageableRequestDto));
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/post")
    public String getPost() {
        return "post";
    }

    @GetMapping("/write")
    public String write() {
//        return "write";
        return "editor";
    }

}