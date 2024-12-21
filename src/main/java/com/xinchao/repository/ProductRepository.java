package com.xinchao.repository;

import com.xinchao.models.Category;
import com.xinchao.models.Product;
import com.xinchao.models.Status;
import com.xinchao.models.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByType(Category category);
    List<Product> findByStatus(Status status);
    List<Product> findByStatus_Name(StatusEnum statusEnum);

}
