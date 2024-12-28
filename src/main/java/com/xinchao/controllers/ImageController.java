package com.xinchao.controllers;

import com.xinchao.endpoints.ImageApiEndpoints;
import com.xinchao.models.Image;
import com.xinchao.security.AllRolePermission;
import com.xinchao.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllRolePermission
@RequestMapping(ImageApiEndpoints.BASE_URL_Image)
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(ImageApiEndpoints.GET_IMAGE_BY_URL)
    @AllRolePermission
    public ResponseEntity<byte[]> getImageByName(@PathVariable String imageUrl) {
        return imageService.getImageByName(imageUrl);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @AllRolePermission
    public ResponseEntity<List<Image>> uploadImages(@RequestParam("files") List<MultipartFile> files) {
        return imageService.uploadImages(files);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping
    @AllRolePermission
    public ResponseEntity<Void> deleteImages(@RequestBody List<String> imageIds) {
        imageService.deleteImages(imageIds);
        return ResponseEntity.noContent().build();
    }
}
