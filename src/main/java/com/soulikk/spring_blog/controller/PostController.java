package com.soulikk.spring_blog.controller;


import com.soulikk.spring_blog.dto.PageRequestDto;
import com.soulikk.spring_blog.dto.PostDto;
import com.soulikk.spring_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


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
    public String writePage() {
        return "editor";
    }

    @PostMapping("/write")
    public String write(PostDto postDto) {
        postService.register(postDto);
        return "redirect:/";
    }

}