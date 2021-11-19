package com.soulikk.spring_blog.repository;

import com.soulikk.spring_blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PostRepository extends JpaRepository<Post, Long> {


    Page<Post> findAll(Pageable pageable);

    @Query("SELECT p, i FROM Post p " +
            "LEFT OUTER JOIN Image i on i.post = p GROUP BY p")
    Page<Object[]> getListPage(Pageable pageable);



}
