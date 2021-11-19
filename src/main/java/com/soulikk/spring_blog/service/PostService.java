package com.soulikk.spring_blog.service;

import com.soulikk.spring_blog.dto.ImageDto;
import com.soulikk.spring_blog.dto.PageRequestDto;
import com.soulikk.spring_blog.dto.PageResultDto;
import com.soulikk.spring_blog.dto.PostDto;
import com.soulikk.spring_blog.entity.Image;
import com.soulikk.spring_blog.entity.Post;
import com.soulikk.spring_blog.entity.User;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface PostService {

    Long register(PostDto postDto);

    PostDto getPost(Long postId);

    PageResultDto<PostDto, Object[]> getList(PageRequestDto pageRequestDto);



    default Map<String, Object> dtoToEntity(PostDto postDto) {


        Map<String, Object> entityMap = new HashMap<>();
        Post post = Post.builder()
                .postId(postDto.getPostId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .user(postDto.getUser())
                .build();
        entityMap.put("post", post);


        return entityMap;
    }

    default PostDto entityToDto(Post post, List<Image> images) {
        PostDto postDto = PostDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();

        List<ImageDto> imageList = images.stream()
                .map(image -> {
                    return ImageDto.builder()
                            .imageId(image.getImageId())
                            .build();
                }).collect(Collectors.toList());

        postDto.setImageDtoList(imageList);

        return postDto;
    }


}
