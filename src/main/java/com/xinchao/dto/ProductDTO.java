package com.xinchao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String type;
    private Integer price;
    private String electricityFee;
    private Integer waterFee;
    private String gasFee;
    private String numberOfTenantsByRoomRate;
    private String address;

    public ProductDTO(String name, String type, Integer price, String electricityFee, Integer waterFee, String gasFee, String numberOfTenantsByRoomRate, String address) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.electricityFee = electricityFee;
        this.waterFee = waterFee;
        this.gasFee = gasFee;
        this.numberOfTenantsByRoomRate = numberOfTenantsByRoomRate;
        this.address = address;
    }


}

