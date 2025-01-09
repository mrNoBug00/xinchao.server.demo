package com.xinchao.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xinchao.models.Image;
import com.xinchao.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
public class DepositToHoldResponse {
    private String id;
    private String name;
    private String phone;
    private Product product;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt;
    private List<Image> contactImage;
    private List<Image> receiptImage;
    private Boolean isViewed;


}
