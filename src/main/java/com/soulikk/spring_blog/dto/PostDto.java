package com.soulikk.spring_blog.dto;



import com.soulikk.spring_blog.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long postId;

    private String title;

    private String content;

    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
