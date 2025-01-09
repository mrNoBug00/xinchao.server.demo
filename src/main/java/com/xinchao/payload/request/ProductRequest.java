package com.xinchao.payload.request;

import com.xinchao.models.Category;
import com.xinchao.models.Status;
import com.xinchao.models.StatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ProductRequest {
    private String name;
    private Integer category;
    private String description;
    private Integer price;
    private String electricityFee;
    private Integer waterFee;
    private String gasFee;
    private String numberOfTenantsByRoomRate;
    private String city;
    private String area;
    private Integer statusId;

}
