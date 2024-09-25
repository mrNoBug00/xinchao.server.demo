package com.xinchao.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
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

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getRoom() {
        return room;
    }

    public void setRoom(Product room) {
        this.room = room;
    }

    public String getBookerName() {
        return bookerName;
    }

    public void setBookerName(String bookerName) {
        this.bookerName = bookerName;
    }

    public String getBookerPhone() {
        return bookerPhone;
    }

    public void setBookerPhone(String bookerPhone) {
        this.bookerPhone = bookerPhone;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getAdminId() {
        return adminId;
    }

    public void setAdminId(User adminId) {
        this.adminId = adminId;
    }

    public String getId() {
        return id;
    }



    public String getRefuseOrCancelMessage() {
        return refuseOrCancelMessage;
    }

    public void setRefuseOrCancelMessage(String refuseOrCancelMessage) {
        this.refuseOrCancelMessage = refuseOrCancelMessage;
    }

    public List<Image> getContactQrCode() {
        return contactQrCode;
    }

    public void setContactQrCode(List<Image> contactQrCode) {
        this.contactQrCode = contactQrCode;
    }
}
