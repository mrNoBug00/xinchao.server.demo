package com.xinchao.endpoints;

public class ApiEndpoints {

    public static final String BASE_URL = "/api/v1";

    //auth api endpoint
    public static final String LOGIN = AuthApiEndpoints.BASE_URL_AUTH + AuthApiEndpoints.AUTH_LOGIN;
    public static final String REGISTER = AuthApiEndpoints.BASE_URL_AUTH + AuthApiEndpoints.AUTH_REGISTER;

    //product api endpoint
    public static final String GET_ALL_PRODUCTS = ProductApiEndpoints.GET_ALL_PRODUCTS;
    public static final String GET_PRODUCTS_BY_ID = ProductApiEndpoints.BASE_URL_PRODUCT + ProductApiEndpoints.GET_PRODUCT_BY_ID;
    public static final String GET_PRODUCTS_BY_CATEGORY = ProductApiEndpoints.BASE_URL_PRODUCT + ProductApiEndpoints.GET_PRODUCTS_BY_CATEGORY;
    public static final String GET_PRODUCTS_BY_STATUS = ProductApiEndpoints.BASE_URL_PRODUCT + ProductApiEndpoints.GET_PRODUCTS_BY_STATUS;
    public static final String GET_PRODUCTS_BY_PAGINATED = ProductApiEndpoints.BASE_URL_PRODUCT + ProductApiEndpoints.GET_PRODUCTS_BY_PAGINATED;

    //image api endpoint
    public static final String GET_IMAGE_BY_URL = ImageApiEndpoints.BASE_URL_Image + ImageApiEndpoints.GET_IMAGE_BY_URL;
    public static final String UPLOAD_IMAGE = ImageApiEndpoints.BASE_URL_Image;

    //contract api endpoint
    public static final String CREATE_CONTRACT = ContractApiEndpoints.BASE_URL_CONTRACT + ContractApiEndpoints.CREATE_CONTRACT;

    //regulation api endpoint
    public static final String GET_ALL_REGULATIONS = RegulationApiEndpoints.GET_ALL_REGULATIONS;

    //company api endpoint
    public static final String GET_ALL_COMPANY_INFO = CompanyInfoApiEndpoints.GET_ALL_COMPANY_INFO;

    //category api endpoint
    public static final String GET_ALL_CATEGORY = CategoryApiEndpoints.GET_ALL_CATEGORY;

    //booking api endpoint
    public static final String CREATE_BOOKING = BookingApiEndpoints.CREATE_BOOKING;

    //depposit to hold
    public static final String CREATE_DEPOSIT  = DepositToHoldEnpoints.BASE_URL_DEPOSIT_TO_HOLD + DepositToHoldEnpoints.CREATE_DEPOSIT;

    public static final String[] PUBLIC_API_ENDPOINTS = {
            LOGIN,
            REGISTER,
            GET_IMAGE_BY_URL,
            UPLOAD_IMAGE,
            GET_ALL_PRODUCTS,
            GET_PRODUCTS_BY_CATEGORY,
            GET_PRODUCTS_BY_STATUS,
            GET_PRODUCTS_BY_ID,
            GET_PRODUCTS_BY_PAGINATED,
            CREATE_CONTRACT,
            GET_ALL_REGULATIONS,
            GET_ALL_COMPANY_INFO,
            GET_ALL_CATEGORY,
            CREATE_BOOKING,
            CREATE_DEPOSIT
    };


}



