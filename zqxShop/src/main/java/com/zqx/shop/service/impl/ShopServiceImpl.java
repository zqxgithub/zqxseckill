package com.zqx.shop.service.impl;

import com.zqx.commom.entity.ShopInfo;
import com.zqx.commom.entity.UserInfo;
import com.zqx.commom.form.ShopApplyForm;
import com.zqx.commom.form.ShopSearchForm;
import com.zqx.commom.form.UserRegisterForm;
import com.zqx.commom.utils.Md5Util;
import com.zqx.commom.vo.ShopInfoCondition;
import com.zqx.commom.vo.ShopInfoVo;
import com.zqx.shop.mapper.ShopMapper;
import com.zqx.shop.service.ShopService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    public void saveShopInfo(ShopApplyForm shopApplyForm) {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setMerchantId(shopApplyForm.getMerchantId());
        shopInfo.setProvince(shopApplyForm.getProvince());
        shopInfo.setCity(shopApplyForm.getCity());
        shopInfo.setBusinessLicense(shopApplyForm.getBusinessLicense());
        shopInfo.setShopName(shopApplyForm.getShopName());
        shopInfo.setShopDescription(shopApplyForm.getShopDescription());
        shopInfo.setShopBusinessScope(shopApplyForm.getShopBusinessScope());
        shopInfo.setState(0);
        shopInfo.setCreatetime(new Date());
        shopMapper.saveShopInfo(shopInfo);
    }

    public List<ShopInfo> listShopInfoBy(ShopSearchForm shopSearchForm) {

        ShopInfoVo shopInfoVo = new ShopInfoVo();
        ShopInfoCondition shopInfoCondition = new ShopInfoCondition();
        if (StringUtils.isNotBlank(shopSearchForm.getShopName())) {
            shopInfoCondition.setShopName(shopSearchForm.getShopName());
        }
        if (StringUtils.isNotBlank(shopSearchForm.getState())) {
            shopInfoCondition.setState(Integer.valueOf(shopSearchForm.getState().trim()));
        }
        shopInfoVo.setShopInfoCondition(shopInfoCondition);
        return shopMapper.listShopInfoBy(shopInfoVo);
    }

    public void updateInfoBy(ShopInfo shopInfo) {
        shopMapper.updateInfoBy(shopInfo);
    }
}
