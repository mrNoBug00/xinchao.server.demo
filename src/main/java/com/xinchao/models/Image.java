package com.xinchao.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "images")
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String imageUrl;
    private String imagePath; // Đường dẫn đến tệp ảnh lưu trữ trong hệ thống file


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true, foreignKey = @ForeignKey(name = "fk_product_image"))
    @JsonBackReference
    private Product product;


    @ManyToOne
    @JoinColumn(name = "deposit_to_hold_id", nullable = true, foreignKey = @ForeignKey(name = "fk_deposit_to_hold_image"))
    @JsonBackReference
    private DepositToHold depositToHold;


}
