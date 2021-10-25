package com.soulikk.spring_blog.service;

import com.soulikk.spring_blog.dto.PageRequestDto;
import com.soulikk.spring_blog.dto.PageResultDto;
import com.soulikk.spring_blog.dto.PostDto;
import com.soulikk.spring_blog.entity.Post;
import com.soulikk.spring_blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Long register(PostDto postDto) {

        Map<String, Object> entityMap = dtoToEntity(postDto);
        Post post = (Post) entityMap.get("post");
        postRepository.save(post);

        return post.getPostId();
    }

    @Override
    public PageResultDto<PostDto, Object[]> getList(PageRequestDto pageRequestDto) {

        Function<Object[], PostDto> fn = (en -> entityToDto((Post)en[0]));

        Page<Post> result = postRepository.findAll(pageRequestDto.getPageable(Sort.by("postId").descending()));
        return new PageResultDto(result, fn);
    }

}
