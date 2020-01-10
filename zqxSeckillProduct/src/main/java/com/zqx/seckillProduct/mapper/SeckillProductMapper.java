package com.zqx.seckillProduct.mapper;

import com.zqx.commom.entity.SeckillProductInfo;
import com.zqx.commom.vo.SeckillProductInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeckillProductMapper {

    public void saveSeckillProduct(SeckillProductInfo seckillProductInfo);

    //查询秒杀商品列表
    List<SeckillProductInfo> listSeckillInfo(SeckillProductInfoVo seckillProductInfoVo);

    void updateSeckillInfoState(SeckillProductInfo seckillProductInfo);
}
