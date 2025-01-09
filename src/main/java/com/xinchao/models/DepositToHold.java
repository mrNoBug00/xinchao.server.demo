package com.xinchao.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class DepositToHold {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String phone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deposit_to_hold_product_id")
    @JsonManagedReference
    private Product product;
    private Date createAt;


    @OneToMany(mappedBy = "depositToHold", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Image> contactImage;

    @OneToMany(mappedBy = "depositToHold", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Image> receiptImage;

    private Boolean isViewed;
}
