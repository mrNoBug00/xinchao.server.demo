package com.xinchao.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class BookingRequest {
//    private String userId;
    private String roomId;
    private String bookerName;
    private String bookerPhone;
    private LocalDateTime bookingTime;
    private List<String> contactQrCode;


}

