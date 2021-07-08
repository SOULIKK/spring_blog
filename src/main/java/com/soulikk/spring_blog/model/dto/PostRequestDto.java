package com.soulikk.spring_blog.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private Long userId;
}
