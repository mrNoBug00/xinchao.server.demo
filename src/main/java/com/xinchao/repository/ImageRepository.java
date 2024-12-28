package com.xinchao.repository;

import com.xinchao.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findByImageUrl(String imageUrl);
    void deleteByProductId(String productId);
}
