package com.soulikk.spring_blog.repository;

import com.soulikk.spring_blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
