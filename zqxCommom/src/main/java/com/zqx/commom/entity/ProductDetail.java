package com.zqx.commom.entity;

import lombok.Data;

@Data
public class ProductDetail {
    private int id;
    private int productId;
    private String productPlace;
    private String productBrand;
    private String productDescription;
    private String productWeight;
    private String productDetailPicUrl;
    private String specsAndPack;
}
