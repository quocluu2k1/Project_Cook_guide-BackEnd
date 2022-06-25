package com.project.cookguide.Cook.guide.project.controllers;
import com.project.cookguide.Cook.guide.project.services.IStorageService;
import com.project.cookguide.Cook.guide.project.services.ImageCommentStorageService;
import com.project.cookguide.Cook.guide.project.services.ImageFoodStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/images")
public class ReadImgController {
    @Autowired
    private IStorageService storageService;

    @Autowired
    private ImageCommentStorageService imageCommentStorageService;

    @Autowired
    private ImageFoodStorageService imageFoodStorageService;

    @GetMapping("/avatar/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/comment/{fileName:.+}")
    public ResponseEntity<byte[]> readImageFood(@PathVariable String fileName) {
        try {
            byte[] bytes = imageCommentStorageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/food/{fileName:.+}")
    public ResponseEntity<byte[]> readImageComment(@PathVariable String fileName) {
        try {
            byte[] bytes = imageFoodStorageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }
}
