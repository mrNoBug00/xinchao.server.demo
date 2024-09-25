package com.xinchao.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    //@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    //@JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private List<Image> identificationCard;
    private String identificationId;
    private String phone;
    private String lessor;
    private String renter;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate rentTimeFrom;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate  rentTimeTo;

    //@JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    private String productType;
    private String EquipmentProvidedByTheLessor;
    private Integer numberOfRenter;
    private Integer rentFee;
    private Integer dayOfPayRentFee;
    private String electricityFee;
    private String waterFee;
    private Integer tenancyDeposit;
    private String regulations;
    private Boolean agree;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private Image signature;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;

    @CreationTimestamp
    private LocalDateTime createAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(List<Image> identificationCard) {
        this.identificationCard = identificationCard;
    }

    public String getIdentificationId() {
        return identificationId;
    }

    public void setIdentificationId(String identificationId) {
        this.identificationId = identificationId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLessor() {
        return lessor;
    }

    public void setLessor(String lessor) {
        this.lessor = lessor;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public LocalDate  getRentTimeFrom() {
        return rentTimeFrom;
    }

    public void setRentTimeFrom(LocalDate  rentTimeFrom) {
        this.rentTimeFrom = rentTimeFrom;
    }

    public LocalDate  getRentTimeTo() {
        return rentTimeTo;
    }

    public void setRentTimeTo(LocalDate  rentTimeTo) {
        this.rentTimeTo = rentTimeTo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getEquipmentProvidedByTheLessor() {
        return EquipmentProvidedByTheLessor;
    }

    public void setEquipmentProvidedByTheLessor(String equipmentProvidedByTheLessor) {
        EquipmentProvidedByTheLessor = equipmentProvidedByTheLessor;
    }

    public Integer getNumberOfRenter() {
        return numberOfRenter;
    }

    public void setNumberOfRenter(Integer numberOfRenter) {
        this.numberOfRenter = numberOfRenter;
    }

    public Integer getRentFee() {
        return rentFee;
    }

    public void setRentFee(Integer rentFee) {
        this.rentFee = rentFee;
    }

    public Integer getDayOfPayRentFee() {
        return dayOfPayRentFee;
    }

    public void setDayOfPayRentFee(Integer dayOfPayRentFee) {
        this.dayOfPayRentFee = dayOfPayRentFee;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(String waterFee) {
        this.waterFee = waterFee;
    }

    public Integer getTenancyDeposit() {
        return tenancyDeposit;
    }

    public void setTenancyDeposit(Integer tenancyDeposit) {
        this.tenancyDeposit = tenancyDeposit;
    }

    public String getRegulations() {
        return regulations;
    }

    public void setRegulations(String regulations) {
        this.regulations = regulations;
    }

    public Boolean getAgree() {
        return agree;
    }

    public void setAgree(Boolean agree) {
        this.agree = agree;
    }

    public Image getSignature() {
        return signature;
    }

    public void setSignature(Image signature) {
        this.signature = signature;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }


}
