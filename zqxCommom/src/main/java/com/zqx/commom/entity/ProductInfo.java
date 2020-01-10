package com.zqx.commom.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductInfo {
    private int id;
    private int merchantId;//商户id
    private int productTypeId;//商品类别id
    private String productTitle;
    private String productName;
    private String productPictureUrl;//图片地址
    private Double productPrice;//原价
    private Double productDiscounts;//优惠价格
    private Date createtime;
    private Date updatetime;
    private int state;//商品状态，0申请中1审核通过2退回3上架4下架
    private Date approvetime;//审核时间
    private int productInventory;//库存
    private int shopId;//商铺id

}
