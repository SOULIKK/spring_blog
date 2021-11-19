package com.soulikk.spring_blog.repository;


import com.soulikk.spring_blog.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
