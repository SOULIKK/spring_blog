package com.soulikk.spring_blog.dto;



import com.soulikk.spring_blog.entity.Post;
import com.soulikk.spring_blog.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public PostDto(Optional<Post> post) {
        this.postId = post.get().getPostId();
        this.title = post.get().getTitle();
        this.content = post.get().getContent();
        this.user = post.get().getUser();
        this.createdAt = post.get().getCreatedAt();
        this.updatedAt = post.get().getUpdatedAt();
    }

    @Builder.Default
    private List<ImageDto> imageDtoList = new ArrayList<>();
}
