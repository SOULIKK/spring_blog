package com.soulikk.spring_blog.controller;

import com.soulikk.spring_blog.config.S3.S3Uploader;
import com.soulikk.spring_blog.entity.Image;
import com.soulikk.spring_blog.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
public class ImageController {

    @Autowired
    ResourceLoader resourceLoader;

    private final ImageService imageService;

    private final S3Uploader s3Uploader;


    @PostMapping("/image")
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) {
        try {
            String S3 = s3Uploader.upload(file, "static");
            int end = S3.length();
            S3 = "http://"+S3.substring(8, end);
            Image image = imageService.register(S3);
            return ResponseEntity.ok().body("/image/"+ image.getImageId());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/image/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable Long imageId) {
        try {
            Image image = imageService.getImage(imageId);
            Resource resource = resourceLoader.getResource(image.getFilePath());
            return ResponseEntity.ok().body(resource);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }



}
