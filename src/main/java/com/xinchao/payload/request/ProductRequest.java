package com.xinchao.payload.request;

import com.xinchao.models.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ProductRequest {
    private String name;
    private String type;
    private String description;
    private Integer price;
    private String electricityFee;
    private String waterFee;
    private String gasFee;
    private String numberOfTenantsByRoomRate;
    private String address;
    private Integer statusId;

}
