package com.soulikk.spring_blog.model.dto;


import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private Long userId;

}
