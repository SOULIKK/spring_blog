package com.soulikk.spring_blog.controller;


import com.soulikk.spring_blog.model.dto.CommentRequestDto;
import com.soulikk.spring_blog.model.entity.Comment;
import com.soulikk.spring_blog.model.entity.Post;
import com.soulikk.spring_blog.model.repository.CommentRepository;
import com.soulikk.spring_blog.security.UserDetailsImpl;
import com.soulikk.spring_blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;


    @GetMapping("/comments")
    public List<Comment> getComments(@RequestParam(value = "postId") Long postId) {
        return commentRepository.findByPostIdOrderByModifiredAtDesc(postId);
    }

    @PostMapping("/setComment")
    public Comment setComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        Comment comment = new Comment(commentRequestDto, username);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal.equals("anonymousUser")) {
            throw new IllegalArgumentException("로그인 하지 않은 사용자입니다.");
        }
        return commentRepository.save(comment);
    }


//    @PostMapping("/setComment")
//    public String writeComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        String username = userDetails.getUser().getUsername();
//        System.out.println("유저네임 :::::::::::::::::::: " + username);
//        Comment comment = new Comment(commentRequestDto, username);
//        commentRepository.save(comment);
//        return "redirect:/";
//    }


}
