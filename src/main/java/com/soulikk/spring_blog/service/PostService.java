package com.soulikk.spring_blog.service;


import com.soulikk.spring_blog.model.dto.PostRequestDto;
import com.soulikk.spring_blog.model.entity.Post;
import com.soulikk.spring_blog.model.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;


    public List<Post> getPostsAll() {
        return postRepository.findAll();
    }

    // 조회 (회원 아이디)
    @Transactional
    public List<Post> getPosts(Long userId) {
        return postRepository.findAllByUserId(userId);
    }


    // 작성
    @Transactional
    public Post writePost(PostRequestDto requestDto, Long userId) {
        Post post = new Post(requestDto, userId);
        postRepository.save(post);
        return post;
    }


    // 수정
//    @Transactional
//    public Long updatePost(Long id, PostRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow (
//                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
//        );
//        post.update(requestDto);
//        return post.getId();
//    }


}
