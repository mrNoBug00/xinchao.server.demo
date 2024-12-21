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

    //image api endpoint
    public static final String GET_IMAGE_BY_URL = ImageApiEndpoints.BASE_URL_Image + ImageApiEndpoints.GET_IMAGE_BY_URL;

    //contract api endpoint
    public static final String CREATE_CONTRACT = ContractApiEndpoints.BASE_URL_CONTRACT + ContractApiEndpoints.CREATE_CONTRACT;

    //regulation api endpoint
    public static final String GET_ALL_REGULATIONS = RegulationApiEndpoints.GET_ALL_REGULATIONS;

    //company api endpoint
    public static final String GET_ALL_COMPANY_INFO = CompanyInfoApiEndpoints.GET_ALL_COMPANY_INFO;

    //category api endpoint
    public static final String GET_ALL_CATEGORY = CategoryApiEndpoints.GET_ALL_CATEGORY;



    public static final String[] PUBLIC_API_ENDPOINTS = {
            LOGIN,
            REGISTER,
            GET_IMAGE_BY_URL,
            GET_ALL_PRODUCTS,
            GET_PRODUCTS_BY_CATEGORY,
            GET_PRODUCTS_BY_STATUS,
            GET_PRODUCTS_BY_ID,
            CREATE_CONTRACT,
            GET_ALL_REGULATIONS,
            GET_ALL_COMPANY_INFO,
            GET_ALL_CATEGORY
    };


}
