package com.xinchao.endpoints;

public class ImageApiEndpoints {

    // Base URL cho Image APIs
    public static final String BASE_URL_Image = ApiEndpoints.BASE_URL + "/images";

    // Các endpoint cho Image
    public static final String GET_IMAGE_BY_URL = "/{imageUrl}";
    public static final String UPLOAD_IMAGES = BASE_URL_Image;
    public static final String DELETE_IMAGES = BASE_URL_Image;
}

