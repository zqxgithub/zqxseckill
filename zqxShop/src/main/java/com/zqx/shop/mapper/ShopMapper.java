package com.zqx.shop.mapper;


import com.zqx.commom.entity.ShopInfo;
import com.zqx.commom.vo.ShopInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {

    public void saveShopInfo(ShopInfo shopInfo);

    List<ShopInfo> listShopInfoBy(ShopInfoVo shopInfoVo);

    public void updateInfoBy(ShopInfo shopInfo);
}
