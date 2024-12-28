package com.xinchao.payload.response;

import com.xinchao.models.CompanyInfo;
import com.xinchao.models.Regulations;
import com.xinchao.models.Status;
import com.xinchao.models.StatusEnum;
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
    private CompanyInfo companyInfo; // Added this field
    private List<ImageDTO> identificationCard;
    private String customerName; // Updated field name
    private String identificationId;
    private String phone;
    private String customerLine;
    private String customerZalo;
    private String guarantorName;
    private String guarantorPhone;
    private String guarantorLine;
    private String guarantorZalo;
    private LocalDate rentTimeFrom;
    private LocalDate rentTimeTo;
    private ProductDTO product;
    private String productType;
    private String equipmentProvidedByTheLessor;
    private Integer numberOfRenter;
    private Integer rentFee;
    private Integer dayOfPayRentFee;
    private String electricityFee;
    private Integer waterFee;
    private Integer tenancyDeposit;
    private List<Regulations> regulations;
    private Boolean agree;
    private ImageDTO signature;
    private Status status;
    private LocalDateTime createTime;
}

