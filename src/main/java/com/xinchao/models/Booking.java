package com.xinchao.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product room;

    private String bookerName;
    private String bookerPhone;

    private LocalDateTime bookingTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status; // Trạng thái: PENDING, CONFIRMED, REJECTED

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = true)
    private User adminId; // Admin xác nhận

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    @JsonManagedReference
    private List<Image> contactQrCode;


    private String refuseOrCancelMessage;


}
