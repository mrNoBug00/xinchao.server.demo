package com.xinchao.controllers;

import com.xinchao.dto.AdminActionBookingDto;
import com.xinchao.endpoints.BookingApiEndpoints;
import com.xinchao.payload.request.BookingRequest;
import com.xinchao.payload.response.BookingResponse;
import com.xinchao.security.AdminPermission;
import com.xinchao.security.AllRolePermission;
import com.xinchao.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(BookingApiEndpoints.BASE_URL_BOOKING)
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @PostMapping
    @AllRolePermission
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest) {
        BookingResponse bookingResponse = bookingService.createBooking(bookingRequest);
        return ResponseEntity.ok(bookingResponse);
    }
    @PutMapping(BookingApiEndpoints.CONFIRM_BOOKING)
    @AdminPermission
    public ResponseEntity<BookingResponse> confirmBooking(@RequestBody AdminActionBookingDto adminActionBookingDto) {
        BookingResponse bookingResponse = bookingService.confirmBooking(adminActionBookingDto);
        return ResponseEntity.ok(bookingResponse);
    }

    @PutMapping(BookingApiEndpoints.REFUSE_BOOKING)
    @AdminPermission
    public ResponseEntity<BookingResponse> refuseBooking(@RequestBody AdminActionBookingDto adminActionBookingDto) {
        BookingResponse bookingResponse = bookingService.refuseBooking(adminActionBookingDto);
        return ResponseEntity.ok(bookingResponse);
    }

    @PutMapping(BookingApiEndpoints.CANCEL_BOOKING)
    @AdminPermission
    public ResponseEntity<BookingResponse> cancelBooking(@RequestBody AdminActionBookingDto adminActionBookingDto) {
        BookingResponse bookingResponse = bookingService.cancelBooking(adminActionBookingDto);
        return ResponseEntity.ok(bookingResponse);
    }


    @GetMapping
    @AllRolePermission
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<BookingResponse> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    // Các API khác như getBookingById, v.v.
}
