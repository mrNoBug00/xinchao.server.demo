package com.xinchao.endpoints;

public class BookingApiEndpoints {

    // Base endpoint for Booking APIs
    public static final String BASE_URL_BOOKING = ApiEndpoints.BASE_URL + "/bookings";

    // Booking-related endpoints
    public static final String CREATE_BOOKING = BASE_URL_BOOKING;
    public static final String CONFIRM_BOOKING = "/confirm";
    public static final String REFUSE_BOOKING = "/refuse";
    public static final String CANCEL_BOOKING = "/cancel";
    public static final String GET_ALL_BOOKINGS = BASE_URL_BOOKING;
}

