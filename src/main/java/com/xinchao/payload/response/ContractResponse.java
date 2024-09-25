package com.xinchao.payload.response;

import com.xinchao.models.Status;
import com.xinchao.payload.response.ImageDTO;
import com.xinchao.payload.response.ProductDTO;
import com.xinchao.payload.response.UserDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ContractResponse {
    private String id;
    private UserDTO user;
    private List<ImageDTO> identificationCard;
    private String identificationId;
    private String phone;
    private String lessor;
    private String renter;
    private LocalDate rentTimeFrom;
    private LocalDate rentTimeTo;
    private ProductDTO product;
    private String productType;
    private String equipmentProvidedByTheLessor;
    private Integer numberOfRenter;
    private Integer rentFee;
    private Integer dayOfPayRentFee;
    private String electricityFee;
    private String waterFee;
    private Integer tenancyDeposit;
    private String regulations;
    private Boolean agree;
    private ImageDTO signature;
    private Status status;
    private LocalDateTime createTime;
}

