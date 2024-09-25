package com.xinchao.payload.response;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String name; // chỉ cần các thuộc tính cần thiết
    // thêm các thuộc tính khác nếu cần
}
