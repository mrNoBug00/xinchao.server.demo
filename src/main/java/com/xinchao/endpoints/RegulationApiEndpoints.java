package com.xinchao.endpoints;

public class RegulationApiEndpoints {

    public static final String BASE_URL_REGULATION = ApiEndpoints.BASE_URL + "/regulations";

    public static final String GET_ALL_REGULATIONS = BASE_URL_REGULATION;
    public static final String GET_REGULATION_BY_ID = "/{id}";
    public static final String CREATE_REGULATION = BASE_URL_REGULATION;
    public static final String UPDATE_REGULATION = "/{id}";
    public static final String DELETE_REGULATION = "/{id}";
}
