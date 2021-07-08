package com.soulikk.spring_blog.controller;

import com.soulikk.spring_blog.model.dto.CommentRequestDto;
import com.soulikk.spring_blog.model.entity.Comment;
import com.soulikk.spring_blog.model.repository.CommentRepository;
import com.soulikk.spring_blog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;


    @GetMapping("/api/comments")
    public List<Comment> getComments(@RequestParam(value = "post_id") Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return commentRepository.findByPostIdOrderByModifiredAtDesc(post_id);
    }

    @PostMapping("/api/setComment")
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


    @DeleteMapping("/api/delComment/{id}")
    public Long delComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }





}
