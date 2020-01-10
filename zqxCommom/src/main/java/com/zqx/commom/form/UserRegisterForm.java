package com.zqx.commom.form;

import lombok.Data;

@Data
public class UserRegisterForm {
    private String username;
    private String password;
    private String repassword;//重复密码
    private String telphone;
    private String birthday;
    private String qq;
    private String weixin;
}
