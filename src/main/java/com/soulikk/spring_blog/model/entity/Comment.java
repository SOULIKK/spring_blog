package com.soulikk.spring_blog.model.entity;


import com.soulikk.spring_blog.model.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String commentContent;

    public Comment(CommentRequestDto commentRequestDto, String username, Long userId) {
        this.id = commentRequestDto.getId();
        this.postId = commentRequestDto.getPostId();
//        this.username = commentRequestDto.getUsername();
        this.userId = userId;
        this.username = username;

        this.commentContent = commentRequestDto.getCommentContent();
    }


}
