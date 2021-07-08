package com.soulikk.spring_blog.model.entity;


import com.soulikk.spring_blog.model.dto.CommentRequestDto;
import com.soulikk.spring_blog.model.dto.PostRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;

    public Post(PostRequestDto requestDto, Long userId) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.userId = userId;
    }

}
