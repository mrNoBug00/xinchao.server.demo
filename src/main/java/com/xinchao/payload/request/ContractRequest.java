package com.xinchao.payload.request;

import com.xinchao.models.CompanyInfo;
import com.xinchao.models.Image;
import com.xinchao.models.Regulations;
import com.xinchao.models.User;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ContractRequest {
    private String companyId;
    private String userId;
    private String customerName;
    private List<String> customerIdentificationCardIds;
    private String customerIdentificationId;
    private String customerPhone;
    private String customerLine;
    private String customerZalo;
    private LocalDate rentTimeFrom;
    private LocalDate rentTimeTo;
    private String guarantorName;
    private String guarantorPhone;
    private String guarantorLine;
    private String guarantorZalo;
    private String productId;
    private String productType;
    private String equipmentProvidedByTheLessor;
    private Integer numberOfRenter;
    private Integer rentFee;
    private Integer dayOfPayRentFee;
    private String electricityFee;
    private Integer waterFee;
    private Integer tenancyDeposit;
    private List<String> regulationsId;
    private Boolean agree;
    private String signatureId;
}
