package com.soulikk.spring_blog.controller;

import com.soulikk.spring_blog.model.dto.PostRequestDto;
import com.soulikk.spring_blog.model.entity.Post;
import com.soulikk.spring_blog.model.repository.PostRepository;
import com.soulikk.spring_blog.security.UserDetailsImpl;
import com.soulikk.spring_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class MainController {

    private final PostRepository postRepository;
    private final PostService postService;


    // 포스팅 작성
    @PostMapping("/api/setPost")
    public String writePost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        String username = userDetails.getUser().getUsername();
        postService.writePost(requestDto, userId, username);
        return "redirect:/";
    }

    // 상세페이지 조회
    @GetMapping("/api/detail/{id}")
    public Optional<Post> getPost(@PathVariable Long id) {
        return postRepository.findById(id);
    }


    // 포스팅 삭제
    @DeleteMapping("/api/delPost/{id}")
    public Long delPost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }

    // 수정
    @PutMapping("/api/uptPost/{id}")
    public Long uptPost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        postService.updatePost(id, postRequestDto);
        return id;
    }

    @GetMapping("/api/getUserInfo")
    public Long getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return userId;
    }

}
