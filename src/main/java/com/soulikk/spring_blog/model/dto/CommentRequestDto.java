package com.soulikk.spring_blog.model.dto;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentRequestDto {
    private Long id;
    private Long postId;
    private String username;
    private String commentContent;
    private LocalDateTime regDate, modDate;

}
