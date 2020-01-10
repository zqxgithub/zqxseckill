package com.zqx.commom.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MerChantInfo {
    private int id;
    private String account;
    private String password;
    private String originalPwd;
    private String telphone;
    private String province;
    private String name;
    private String city;
    private String address;
    private Date createtime;
}
