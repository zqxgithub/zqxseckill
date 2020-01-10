package com.zqx.seckillProduct.service.impl;

import com.zqx.commom.constant.Constant;
import com.zqx.commom.entity.ProductInfo;
import com.zqx.commom.entity.SeckillProductInfo;
import com.zqx.commom.form.SeckillForm;
import com.zqx.commom.utils.DateUtils;
import com.zqx.commom.vo.SeckillProductInfoCondition;
import com.zqx.commom.vo.SeckillProductInfoVo;
import com.zqx.seckillProduct.mapper.SeckillProductMapper;
import com.zqx.seckillProduct.service.ProductService;
import com.zqx.seckillProduct.service.SeckillProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SeckillProductServiceImpl implements SeckillProductService {

    @Autowired
    SeckillProductMapper seckillProductMapper;

    @Autowired
    ProductService productService;

    public void saveSeckillProduct(SeckillForm seckillForm){
        SeckillProductInfo seckillProductInfo = new SeckillProductInfo();
        if (seckillForm !=null){
            seckillProductInfo.setProductId(seckillForm.getProductId());
            seckillProductInfo.setSeckillPrice(seckillForm.getSeckillPrice());
            seckillProductInfo.setSeckillNum(0);
            ProductInfo product = productService.findProductById(seckillForm.getProductId());
            seckillProductInfo.setSeckillInventory(product.getProductInventory());
            seckillProductInfo.setCreatetime(new Date());
            seckillProductInfo.setStarttime(DateUtils.tranferTimeBy(seckillForm.getStarttime(),Constant.yyyyMMddhhmmss));
            seckillProductInfo.setEndtime(DateUtils.tranferTimeBy(seckillForm.getEndtime(),Constant.yyyyMMddhhmmss));
            seckillProductInfo.setState(0);
            seckillProductInfo.setShopId(seckillForm.getShopId());
            seckillProductInfo.setProductPrice(seckillForm.getProductPrice());
            seckillProductInfo.setProductName(seckillForm.getProductName());
            seckillProductInfo.setProductTitle(seckillForm.getProductTitle());
            seckillProductMapper.saveSeckillProduct(seckillProductInfo);
        }
    }

    public List<SeckillProductInfo> listSeckillInfo(SeckillForm seckillForm){
        SeckillProductInfoVo seckillProductInfoVo = new SeckillProductInfoVo();
        if (seckillForm !=null){
            SeckillProductInfoCondition condition = new SeckillProductInfoCondition();
            if (seckillForm.getShopId() != -1){
                condition.setShopId(seckillForm.getShopId());
            }
            if (seckillForm.getState() != -1){
                condition.setState(seckillForm.getState());
            }
            seckillProductInfoVo.setSeckillProductInfoCondition(condition);
        }
        return seckillProductMapper.listSeckillInfo(seckillProductInfoVo);
    }

    public void updateSeckillInfoState(int id,int state){
        SeckillProductInfo seckillProductInfo = new SeckillProductInfo();
        seckillProductInfo.setId(id);
        seckillProductInfo.setState(state);
        if (state ==1 || state ==2){
            seckillProductInfo.setApprovetime(new Date());
        }
        seckillProductMapper.updateSeckillInfoState(seckillProductInfo);
    }
}
