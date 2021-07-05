package com.soulikk.spring_blog.controller;


import com.soulikk.spring_blog.model.dto.PostRequestDto;
import com.soulikk.spring_blog.model.entity.Post;
import com.soulikk.spring_blog.model.repository.PostRepository;
import com.soulikk.spring_blog.security.UserDetailsImpl;
import com.soulikk.spring_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;


    // 리스트 조회
    // @GetMapping("/")
    // public List<Post> getList() {
    //     return postRepository.findAll();
    // }


    // 유저별 리스트 조회
     @GetMapping("/api/list")
     public List<Post> getList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return postService.getPosts(userId);
     }


    // 상세페이지 조회
    @GetMapping("/api/post/{id}")
    public Optional<Post> getPost(@PathVariable Long id) {
        return postRepository.findById(id);
    }


    // 작성
    @PostMapping("/api/post")
    public Post writePost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return postService.writePost(requestDto, userId);
    }

    // 수정
    @PostMapping("/api/uptPost/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.updatePost(id, requestDto);
        return id;
    }

    // 삭제
    @DeleteMapping("/api/delpost/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }

}
