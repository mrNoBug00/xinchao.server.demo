package com.xinchao.payload.request;

import com.xinchao.models.Image;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ContractRequest {
    private String userId;
    private List<String> identificationCardIds;
    private String identificationId;
    private String phone;
    private String lessor;
    private String renter;
    private LocalDate rentTimeFrom;
    private LocalDate rentTimeTo;
    private String productId;
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
    private String signatureId;
}
