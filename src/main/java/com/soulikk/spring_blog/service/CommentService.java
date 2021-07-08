package com.soulikk.spring_blog.service;


import com.soulikk.spring_blog.model.dto.CommentRequestDto;
import com.soulikk.spring_blog.model.dto.PostRequestDto;
import com.soulikk.spring_blog.model.entity.Comment;
import com.soulikk.spring_blog.model.entity.Post;
import com.soulikk.spring_blog.model.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    // 조회
    @Transactional
    public List<Comment> getComments(Long postId) {
        return (List<Comment>) commentRepository.findById(postId).orElse(null);
    }

    // 작성
    // @Transactional
    // public Comment writeComment(CommentRequestDto commentRequestDto, String username) {
    //     Comment comment = new Comment(commentRequestDto, username);
    //     commentRepository.save(comment);
    //     return comment;
    // }

}
