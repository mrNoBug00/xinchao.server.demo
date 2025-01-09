package com.xinchao.repository;

import com.xinchao.models.DepositToHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepositToHoldRepository extends JpaRepository<DepositToHold, String> {
    List<DepositToHold> findByIsViewed(Boolean isViewed);




    @Query("SELECT d FROM DepositToHold d WHERE d.product.id = :productId")
    List<DepositToHold> findByProductId(String productId);


    @Modifying
    @Transactional
    @Query("DELETE FROM DepositToHold d WHERE d.product.id = :productId")
    void deleteByProductId(String productId);


}
