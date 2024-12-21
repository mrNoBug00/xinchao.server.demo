package com.xinchao.endpoints;


public class ProductApiEndpoints {

    public static final String BASE_URL_PRODUCT = ApiEndpoints.BASE_URL + "/products";

    public static final String GET_ALL_PRODUCTS = BASE_URL_PRODUCT;
    public static final String GET_PRODUCT_BY_ID = "/{id}";
    public static final String CREATE_PRODUCT = "/create";
    public static final String UPDATE_PRODUCT = "/{id}";
    public static final String DELETE_PRODUCT = "/{id}";
    public static final String GET_PRODUCTS_BY_CATEGORY = "/category/{categoryName}";
    public static final String GET_PRODUCTS_BY_STATUS = "/status/{statusName}";

}

