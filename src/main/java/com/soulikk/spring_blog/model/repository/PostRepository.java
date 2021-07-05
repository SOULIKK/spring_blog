package com.soulikk.spring_blog.model.repository;

import com.soulikk.spring_blog.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository  extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long userId);
}
