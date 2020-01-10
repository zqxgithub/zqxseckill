package com.zqx.commom.form;

import lombok.Data;

@Data
public class ShopApplyForm {
    private int merchantId;
    private String shopName;
    private String shopDescription;
    private String shopBusinessScope;
    private String province;
    private String city;
    private String businessLicense;
}
