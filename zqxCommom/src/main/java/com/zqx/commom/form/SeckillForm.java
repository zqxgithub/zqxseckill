package com.zqx.commom.form;

import lombok.Data;

@Data
public class SeckillForm {
    private int productId;
    private int shopId = -1;
    private String productTitle;//商品标题
    private String productName;//商品名称
    private double productPrice;//商品价格
    private double seckillPrice;//商品秒杀价格

    private String starttime;//开始时间，yyyy-MM-dd hh:mm:ss
    private String endtime;//结束时间,yyyy-MM-dd hh:mm:ss

    private int state = -1;//状态

}
