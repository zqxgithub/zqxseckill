package com.zqx.commom.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ShopInfo {

    private int id;
    private int merchantId;
    private String shopName;
    private String shopDescription;
    private String shopBusinessScope;
    private String province;
    private String city;
    private String businessLicense;
    private Date createtime;
    private int state;//0申请中 1申请通过 2退回 3商铺下架
}