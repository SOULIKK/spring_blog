package com.soulikk.spring_blog.service;

import com.soulikk.spring_blog.dto.PageRequestDto;
import com.soulikk.spring_blog.dto.PageResultDto;
import com.soulikk.spring_blog.dto.PostDto;
import com.soulikk.spring_blog.entity.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PostService {

    Long register(PostDto postDto);

    PageResultDto<PostDto, Object[]> getList(PageRequestDto pageRequestDto);

    default Map<String, Object> dtoToEntity(PostDto postDto) {
        Map<String, Object> entityMap = new HashMap<>();
        Post post = Post.builder()
                .postId(postDto.getPostId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        entityMap.put("post", post);

        return entityMap;
    }

    default PostDto entityToDto(Post post) {
        PostDto postDto = PostDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
        return postDto;
    }
}
