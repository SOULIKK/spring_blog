package com.soulikk.spring_blog.controller;


import com.soulikk.spring_blog.model.dto.PostRequestDto;
import com.soulikk.spring_blog.model.entity.Post;
import com.soulikk.spring_blog.model.repository.PostRepository;
import com.soulikk.spring_blog.security.UserDetailsImpl;
import com.soulikk.spring_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;


    // 전체 조회
    // @GetMapping("/")
    // public List<Post> getList() {
    //     return postRepository.findAll();
    // }


    // 작성 페이지
    @GetMapping("/write")
    public String writePage() {
        return "write";
    }


    // 작성
    @PostMapping("/setPost")
    public String writePost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        postService.writePost(requestDto, userId);
        return "redirect:/";
    }


    // 상세페이지 조회
    @GetMapping("/detail")
    public String getPost(@RequestParam(required = false) Long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "post";
    }


    // 수정
    @PostMapping("/uptPost/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.updatePost(id, requestDto);
        return id;
    }

    // 삭제
    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "/";
    }

}
