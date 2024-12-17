package com.xinchao.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contract")
@Setter
@Getter
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    //@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private CompanyInfo companyInfo;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "identification_card_id")
    private List<Image> customerIdentificationCardIds;

    private String customerName;

    private String identificationId;

    private String phone;

    private String customerLine;

    private String customerZalo;

    private String guarantorName;

    private String guarantorPhone;

    private String guarantorLine;

    private String guarantorZalo;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate rentTimeFrom;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate  rentTimeTo;

    //@JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")

    private Product product;

    private String productType;

    private String equipmentProvidedByTheLessor;

    private Integer numberOfRenter;

    private Integer rentFee;

    private Integer dayOfPayRentFee;

    private String electricityFee;

    private Integer waterFee;

    private Integer tenancyDeposit;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "regulations_id")
    private List<Regulations> regulations;

    private Boolean agree;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "signature_id")
    private Image signatureId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;

    @CreationTimestamp
    private LocalDateTime createAt;


}
