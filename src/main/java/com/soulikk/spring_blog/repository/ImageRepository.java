package com.soulikk.spring_blog.repository;


import com.soulikk.spring_blog.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Modifying
    @Query("UPDATE Image i SET i.post.postId = :postId WHERE i.imageId = :imageId")
    void updatePostId(@Param("postId") Long postId, @Param("imageId") Long imageId);



}
