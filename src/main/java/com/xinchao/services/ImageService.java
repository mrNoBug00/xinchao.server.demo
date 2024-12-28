package com.xinchao.services;

import com.xinchao.exception.ImageSaveException;
import com.xinchao.models.Image;
import com.xinchao.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {


    @Value("${identificationCard.upload.dir}")
    private String identificationCard;

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ResponseEntity<byte[]> getImageByName(String imageUrl) {
        Image image = getImageByUrl(imageUrl);
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

    public ResponseEntity<List<Image>> uploadImages(List<MultipartFile> files) {
        List<Image> savedImages = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                ImageInfo imageInfo = saveImage(file);
                Image savedImage = new Image();
                savedImage.setImageUrl(imageInfo.getUniqueFileName());
                savedImage.setImagePath(String.valueOf(imageInfo.getFilePath()));
                savedImages.add(savedImage);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        return ResponseEntity.ok(saveImages(savedImages));
    }


    public void deleteImages(List<String> imageIds) {
        imageRepository.deleteAllById(imageIds);
    }

    private ImageInfo saveImage(MultipartFile file) throws IOException {
        try {
            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(identificationCard + uniqueFileName);
            Files.copy(file.getInputStream(), filePath);
            return new ImageInfo(uniqueFileName, filePath);
        } catch (IOException e) {
            throw new ImageSaveException("Failed to save image: " + file.getOriginalFilename(), e);
        }
    }

    public class ImageInfo {
        private String uniqueFileName;
        private Path filePath;

        public ImageInfo(String uniqueFileName, Path filePath) {
            this.uniqueFileName = uniqueFileName;
            this.filePath = filePath;
        }

        public String getUniqueFileName() {
            return uniqueFileName;
        }

        public Path getFilePath() {
            return filePath;
        }
    }

    private Image getImageByUrl(String imageUrl) {
        return imageRepository.findByImageUrl(imageUrl);
    }

    private List<Image> saveImages(List<Image> images) {
        return imageRepository.saveAll(images);
    }
}
