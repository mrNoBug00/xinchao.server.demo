package com.xinchao.payload.response;

import com.xinchao.models.Image;
import com.xinchao.models.Status;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String type;
    private String description;
    private Status status;
    private Integer price;
    private String electricityFee;
    private String waterFee;
    private String gasFee;
    private String numberOfTenantsByRoomRate;
    private String address;
    private List<Image> image;
    private UserResponse  author;

    public ProductResponse(String id, String name, String type, String description, Status status, Integer price, String electricityFee, String waterFee, String gasFee, String numberOfTenantsByRoomRate, String address, List<Image> image, UserResponse author) {
        this.id = id;
        this.name = name;
        this.type = type;
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
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(String waterFee) {
        this.waterFee = waterFee;
    }

    public String getGasFee() {
        return gasFee;
    }

    public void setGasFee(String gasFee) {
        this.gasFee = gasFee;
    }

    public String getNumberOfTenantsByRoomRate() {
        return numberOfTenantsByRoomRate;
    }

    public void setNumberOfTenantsByRoomRate(String numberOfTenantsByRoomRate) {
        this.numberOfTenantsByRoomRate = numberOfTenantsByRoomRate;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public UserResponse  getAuthor() {
        return author;
    }

    public void setAuthor(UserResponse  author) {
        this.author = author;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
