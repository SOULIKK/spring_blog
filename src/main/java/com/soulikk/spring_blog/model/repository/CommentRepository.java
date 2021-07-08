package com.soulikk.spring_blog.model.repository;


import com.soulikk.spring_blog.model.entity.Comment;
import com.soulikk.spring_blog.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comment c WHERE c.post_id = :post_id order by modified_at desc", nativeQuery = true)
    List<Comment> findByPostIdOrderByModifiredAtDesc(@Param(value = "post_id") Long post_id);
}
