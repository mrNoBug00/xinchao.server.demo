package com.xinchao.services;

import com.xinchao.models.Image;
import com.xinchao.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image getImageByUrl(String imageUrl) {
        return imageRepository.findByImageUrl(imageUrl);
    }

    public List<Image> saveImages(List<Image> images) {
        return imageRepository.saveAll(images);
    }

    public void deleteImages(List<String> imageIds) {
        imageRepository.deleteAllById(imageIds);
    }

}
