package com.xinchao.controllers;

import com.xinchao.endpoints.ImageApiEndpoints;
import com.xinchao.models.Image;
import com.xinchao.security.AllRolePermission;
import com.xinchao.services.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllRolePermission
@RequestMapping(ImageApiEndpoints.BASE_URL_Image)
public class ImageController {

    private final ImageService imageService;


    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Value("${identificationCard.upload.dir}")
    private String identificationCard;
    @CrossOrigin(origins = "*")
    @GetMapping(ImageApiEndpoints.GET_IMAGE_BY_URL)
    public ResponseEntity<byte[]> getImageByName(@PathVariable String imageUrl) {
        Image image = imageService.getImageByUrl(imageUrl);
        if (image != null) {
            try {
                Path imagePath = Paths.get(image.getImagePath());
                byte[] imageData = Files.readAllBytes(imagePath);
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(imageData);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @CrossOrigin(origins = "*")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Image>> uploadImages(@RequestParam("files") List<MultipartFile> files) {
        List<Image> savedImages = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                String imagePath = saveFile(file);
                Image image = new Image();
                image.setImageUrl(file.getOriginalFilename());
                image.setImagePath(imagePath);
                savedImages.add(image);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        return ResponseEntity.ok(imageService.saveImages(savedImages));
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping
    public ResponseEntity<Void> deleteImages(@RequestBody List<String> imageIds) {
        imageService.deleteImages(imageIds);
        return ResponseEntity.noContent().build();
    }

    private String saveFile(MultipartFile file) throws IOException {
        String directory = identificationCard;
        Path filePath = Paths.get(directory, file.getOriginalFilename());
        Files.write(filePath, file.getBytes());
        return filePath.toString();
    }
}