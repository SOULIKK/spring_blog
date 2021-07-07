package com.soulikk.spring_blog.model.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private Long id;
    private Long postId;
    private String username;
    private String commentContent;

}
