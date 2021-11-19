package com.soulikk.spring_blog.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column
    private String fileName;

    @Column
    private String saveFileName;

    @Column
    private String filePath;

    @Column
    private String contentType;

    private long size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;
}
