package com.heima.user.response;

import lombok.Data;

@Data
public class UserLoginVO {
    private String token;
    private Long userId;
    private String username;
    private Integer balance;
}
