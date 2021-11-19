package com.soulikk.spring_blog.controller;

import com.soulikk.spring_blog.entity.Image;
import com.soulikk.spring_blog.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
public class ImageController {

    @Autowired
    ResourceLoader resourceLoader;

    private final ImageService imageService;


    @PostMapping("/image")
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) {
        try {
            Image image = imageService.register(file);
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
            Resource resource = resourceLoader.getResource("file:" + image.getFilePath());
            return ResponseEntity.ok().body(resource);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
