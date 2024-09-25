package com.xinchao.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String type;
    private Integer price;
    private String electricityFee;
    private String waterFee;
    private String gasFee;
    private String numberOfTenantsByRoomRate;

    private String address;
    @Column(length = 1000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private List<Image> imageUrl;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<Image> imageUrl) {
        this.imageUrl = imageUrl;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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
