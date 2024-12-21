package com.xinchao.endpoints;

public class StatusApiEndpoints {

    public static final String BASE_URL_STATUS = ApiEndpoints.BASE_URL + "/statuses";

    public static final String GET_ALL_STATUSES = BASE_URL_STATUS;
    public static final String ADD_STATUS = BASE_URL_STATUS;
    public static final String DELETE_STATUS = "/{id}";
    public static final String GET_STATUS_BY_NAME = "/{name}";
}

