package com.zqx.commom.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SeckilUserResult {
    private int id;
    private int userId;
    private int productId;
    private int seckillId;
    private int result;
    private String resultdata;
    private Date secktime;

}
