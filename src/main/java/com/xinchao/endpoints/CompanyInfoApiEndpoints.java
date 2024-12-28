package com.xinchao.endpoints;

public class CompanyInfoApiEndpoints {

    // Base URL cho CompanyInfo APIs
    public static final String BASE_URL_COMPANY_INFO = ApiEndpoints.BASE_URL + "/company-info";

    // Các endpoint cho CompanyInfo
    public static final String GET_ALL_COMPANY_INFO = BASE_URL_COMPANY_INFO;
    public static final String CREATE_COMPANY_INFO = BASE_URL_COMPANY_INFO;
    public static final String UPDATE_COMPANY_INFO = "/{id}";
    public static final String DELETE_COMPANY_INFO = "/{id}";
}
