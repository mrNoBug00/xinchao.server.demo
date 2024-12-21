package com.xinchao.payload.response;

import com.xinchao.models.Image;
import com.xinchao.models.Status;
import com.xinchao.models.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class BookingResponse {
    private String id;
    private String userId;
    private String roomId;
    private String roomName;
    private String roomAddress;
    private String bookerName;
    private String bookerPhone;
    private LocalDateTime bookingTime;
    private Status status;
    private String confirmedBy;
    private String refuseBy;
    private String cancelBy;
    private String refuseOrCancelMessage;
    private List<Image> contactQrCode;

    public BookingResponse(String id, String userId, String roomId, String roomName, String roomAddress,
                           String bookerName, String bookerPhone, LocalDateTime bookingTime,
                           Status status, String confirmedBy, String refuseBy,
                           String cancelBy, String refuseOrCancelMessage, List<Image> contactQrCode) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomAddress = roomAddress;
        this.bookerName = bookerName;
        this.bookerPhone = bookerPhone;
        this.bookingTime = bookingTime;
        this.status = status;
        this.confirmedBy = confirmedBy;
        this.refuseBy = refuseBy;
        this.cancelBy = cancelBy;
        this.refuseOrCancelMessage = refuseOrCancelMessage;
        this.contactQrCode = contactQrCode;
    }

}
