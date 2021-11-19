package com.soulikk.spring_blog.controller;


import com.soulikk.spring_blog.dto.PageRequestDto;
import com.soulikk.spring_blog.dto.PostDto;
import com.soulikk.spring_blog.entity.User;
import com.soulikk.spring_blog.security.UserDetailsImpl;
import com.soulikk.spring_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Log4j2
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String getPostList(PageRequestDto pageRequestDto, Model model) {
        model.addAttribute("posts", postService.getList(pageRequestDto));
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
    public String write(PostDto postDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        postDto.setUser(user);
        postService.register(postDto);
        return "redirect:/";
    }

    @GetMapping("/post/{postId}")
    public String detail(@PathVariable Long postId, Model model) {
        PostDto postDto = postService.getPost(postId);
        model.addAttribute("post", postDto);
        return "post2";
    }

}