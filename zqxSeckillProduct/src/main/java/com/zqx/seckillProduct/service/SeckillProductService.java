package com.zqx.seckillProduct.service;

import com.zqx.commom.entity.SeckillProductInfo;
import com.zqx.commom.form.SeckillForm;
import com.zqx.commom.vo.SeckillProductInfoVo;

import java.util.List;

public interface SeckillProductService {

    public void saveSeckillProduct(SeckillForm seckillForm);

    List<SeckillProductInfo> listSeckillInfo(SeckillForm seckillForm);
    public void updateSeckillInfoState(int id,int state);

}
