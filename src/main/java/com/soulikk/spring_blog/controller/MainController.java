package com.soulikk.spring_blog.controller;

import com.soulikk.spring_blog.model.entity.Post;
import com.soulikk.spring_blog.model.repository.PostRepository;
import com.soulikk.spring_blog.security.UserDetailsImpl;
import com.soulikk.spring_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class MainController {

    private final PostRepository postRepository;
    private final PostService postService;

//    @GetMapping("/")
//    public String main(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        model.addAttribute("username", userDetails.getUsername());
//        Long userId = userDetails.getUser().getId();
//        model.addAttribute("posts", postService.getPosts(userId));
//        return "index";
//    }

     @GetMapping("/")
     public String getList(Model model) {
         model.addAttribute("posts", postService.getPostsAll());
         return "index";
     }

}
