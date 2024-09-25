package com.xinchao.payload.request;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class BookingRequest {
    private String userId;
    private String roomId;
    private String bookerName;
    private String bookerPhone;
    private LocalDateTime bookingTime;
    private List<String> contactQrCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
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

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public List<String> getContactQrCode() {
        return contactQrCode;
    }

    public void setContactQrCode(List<String> contactQrCode) {
        this.contactQrCode = contactQrCode;
    }
}

