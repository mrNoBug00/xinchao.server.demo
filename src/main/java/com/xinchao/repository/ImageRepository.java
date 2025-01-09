package com.xinchao.repository;

import com.xinchao.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findByImageUrl(String imageUrl);

    @Modifying
    @Transactional
    @Query("DELETE FROM Image i WHERE i.product.id = :productId")
    void deleteByProductId(String productId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Image i WHERE i.depositToHold.id = :depositToHoldId")
    void deleteByDepositToHoldId(@Param("depositToHoldId") String depositToHoldId);




}