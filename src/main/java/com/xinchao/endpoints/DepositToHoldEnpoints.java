package com.xinchao.endpoints;

public class DepositToHoldEnpoints {
    public static final String BASE_URL_DEPOSIT_TO_HOLD = ApiEndpoints.BASE_URL + "/deposits";

    public static final String GET_ALL_DEPOSIT = BASE_URL_DEPOSIT_TO_HOLD;
    public static final String CREATE_DEPOSIT = "/create";
    public static final String DELETE_DEPOSIT = "/{id}";
    public static final String UPDATE_DEPOSIT = "/{id}";
    public static final String GET_DEPOSIT_BY_ID = "/{id}";
}
