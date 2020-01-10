package com.zqx.commom.form;

import lombok.Data;

@Data
public class MerchantCheckInForm {
    private String account;
    private String password;
    private String repassword;
    private String telphone;
    private String province;
    private String name;
    private String city;
    private String address;
}
