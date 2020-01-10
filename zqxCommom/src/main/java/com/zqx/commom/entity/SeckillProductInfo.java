package com.zqx.commom.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SeckillProductInfo {

    private Integer id;
    private Integer productId;
    private Integer seckillNum;
    private Double seckillPrice;
    private Integer seckillInventory;
    private Date createtime;
    private Date starttime;
    private Date endtime;
    private Integer shopId;
    private Integer state;
    private Double productPrice;
    private String productTitle;
    private String productName;
    private Date approvetime;
    private Integer seckillVersion;


}
