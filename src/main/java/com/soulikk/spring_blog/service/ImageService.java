package com.soulikk.spring_blog.service;

import com.soulikk.spring_blog.entity.Image;
import com.soulikk.spring_blog.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    private final Path uploadLocation;

    public ImageService(String uploadPath) {
        this.uploadLocation = Paths.get(uploadPath);
    }



    public Image getImage(Long imageId) {
        return imageRepository.findById(imageId).get();
    }


    public Image register(MultipartFile file) throws Exception {

        try {
            if (file.isEmpty()) {
                throw new Exception("존재하지 않는 파일");
            }
            String saveFileName = fileSave(uploadLocation.toString(), file);
            Image saveFile = new Image();
            saveFile.setFileName(file.getOriginalFilename());
            saveFile.setSaveFileName(saveFileName);
            saveFile.setContentType(file.getContentType());
            saveFile.setSize(file.getResource().contentLength());
            saveFile.setFilePath(uploadLocation.toString().replace(File.separatorChar, '/') +'/' + saveFileName);

            imageRepository.save(saveFile);
            return saveFile;

        } catch(IOException e) {
            throw new Exception("저장 실패", e);
        }
    }

    public String fileSave(String uploadLocation, MultipartFile file) throws IOException {
        File uploadDir = new File(uploadLocation);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid.toString() + file.getOriginalFilename();
        File saveFile = new File(uploadLocation, saveFileName);
        FileCopyUtils.copy(file.getBytes(), saveFile);

        return saveFileName;
    }
}
