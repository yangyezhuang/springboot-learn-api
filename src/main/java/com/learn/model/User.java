package com.learn.model;

import lombok.Data;

/**
 * User实体类
 */
@Data
public class User {

    private int uid;
    private String username;
    private String password;
    private String phoneNum;
    private String email;
    private boolean status;
    private String role;
    private int verifyCode;

}
