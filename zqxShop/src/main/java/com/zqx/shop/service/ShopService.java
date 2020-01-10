package com.zqx.shop.service;

import com.zqx.commom.entity.ShopInfo;
import com.zqx.commom.entity.UserInfo;
import com.zqx.commom.form.ShopApplyForm;
import com.zqx.commom.form.ShopSearchForm;
import com.zqx.commom.form.UserRegisterForm;
import com.zqx.commom.vo.ShopInfoVo;

import java.util.List;

public interface ShopService {

    public void saveShopInfo(ShopApplyForm shopApplyForm);

    public List<ShopInfo> listShopInfoBy(ShopSearchForm shopSearchForm);

    public void updateInfoBy(ShopInfo shopInfo);
}
