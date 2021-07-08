package com.soulikk.spring_blog.controller;



import com.soulikk.spring_blog.model.entity.Comment;
import com.soulikk.spring_blog.model.entity.Post;
import com.soulikk.spring_blog.model.repository.CommentRepository;
import com.soulikk.spring_blog.model.repository.PostRepository;


import com.soulikk.spring_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final PostService postService;


    @GetMapping("/")
    public String getList(Model model, @PageableDefault(size = 6) Pageable pageable) {
        // model.addAttribute("posts", postService.getPostsAll());
        Page<Post> posts = postRepository.findAll(pageable);
        int startPage = Math.max(1, posts.getPageable().getPageNumber() - 4);
        int endPage = Math.min(posts.getTotalPages(), posts.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("posts", posts);
        return "index";
    }

    // 작성 페이지
    @GetMapping("/api/write")
    public String writePage() {
        return "write";
    }



    @GetMapping("/api/comment")
    public List<Comment> getComment(@RequestParam("postId") Long postId) {
        return commentRepository.findByPostIdOrderByModifiredAtDesc(postId);
    }


    // 수정
    // @PostMapping("/api/uptPost/{id}")
    //   public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
    //   postService.updatePost(id, requestDto);
    //   return id;
    // }


}
