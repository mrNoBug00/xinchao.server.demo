package com.xinchao.endpoints;

public class AuthApiEndpoints {

    public static final String BASE_URL_AUTH = ApiEndpoints.BASE_URL + "/auth";
    public static final String AUTH_REGISTER = "/register";
    public static final String AUTH_LOGIN = "/login";
    public static final String AUTH_UPDATE_USER = "/{id}";
    public static final String AUTH_DELETE_USER = "/{userId}";
    public static final String CREATE_ADMIN = "/create/admins";
}
