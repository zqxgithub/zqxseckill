package com.zqx.commom.entity;

import lombok.Data;

@Data
public class UserInfo {
    private int id;
    private String username;
    private String password;
    private String originalPwd;
    private String telphone;
    private String birthday;
    private String qq;
    private String weixin;
    private String idCard;
    private String name;
    private int age;
    private int gender;
}
