package com.xinchao.endpoints;

public class CategoryApiEndpoints {
    public static final String BASE_URL_CATEGORY = ApiEndpoints.BASE_URL + "/category";

    public static final String GET_ALL_CATEGORY = BASE_URL_CATEGORY;
    public static final String ADD_CATEGORY = BASE_URL_CATEGORY;
    public static final String DELETE_CATEGORY = "/{id}";
    public static final String GET_CATEGORY_BY_ID = "/{id}";
}
