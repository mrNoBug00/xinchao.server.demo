package com.xinchao.payload.response;

import com.xinchao.models.*;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private Category category;
    private String description;
    private Status status;
    private Integer price;
    private String electricityFee;
    private Integer waterFee;
    private String gasFee;
    private String numberOfTenantsByRoomRate;
    private String address;
    private List<Image> image;
    private UserResponse  author;
    private CompanyInfo companyInfo;

    public ProductResponse(String id, String name, Category category, String description, Status status, Integer price, String electricityFee, Integer waterFee, String gasFee, String numberOfTenantsByRoomRate, String address, List<Image> image, UserResponse author, CompanyInfo companyInfo) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.status = status;
        this.price = price;
        this.electricityFee = electricityFee;
        this.waterFee = waterFee;
        this.numberOfTenantsByRoomRate = numberOfTenantsByRoomRate;
        this.gasFee = gasFee;
        this.address = address;
        this.image = image;
        this.author = author;
        this.companyInfo = companyInfo;
    }


}
