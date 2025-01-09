package com.xinchao.payload.request;

import com.xinchao.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DepositToHoldRequest {
    private String name;
    private String phone;
    private String productId;
    private Date createAt;
    private List<String> contactImageId;
    private List<String> receiptImageId;
    private Boolean isViewed;
}
