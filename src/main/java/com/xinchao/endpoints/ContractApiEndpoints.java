package com.xinchao.endpoints;

public class ContractApiEndpoints {

    public static final String BASE_URL_CONTRACT = ApiEndpoints.BASE_URL + "/contracts";

    public static final String GET_ALL_CONTRACTS = BASE_URL_CONTRACT;
    public static final String GET_CONTRACT_BY_ID = "/{id}";
    public static final String CREATE_CONTRACT = "/create";
    public static final String UPDATE_CONTRACT = "/{id}";
    public static final String DELETE_CONTRACT = "/{id}";
    public static final String SIGN_CONTRACT_CONFIRM = "/{id}/signConfirm";
}
