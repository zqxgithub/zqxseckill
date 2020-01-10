package com.zqx.product.service.impl;

import com.zqx.commom.entity.ProductInfo;
import com.zqx.commom.form.ProductForm;
import com.zqx.commom.vo.ProductInfoCondition;
import com.zqx.commom.vo.ProductInfoVo;
import com.zqx.product.mapper.ProductInfoMapper;
import com.zqx.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    public void saveProductInfo(ProductForm productForm){
        ProductInfo productInfo = new ProductInfo();
        Double productPrice = Double.valueOf(productForm.getProductPriceParam());//获取价格
        productInfo.setProductPrice(productPrice);
        productInfo.setProductName(productForm.getProductName());
        productInfo.setProductTitle(productForm.getProductTitle());
        productInfo.setMerchantId(productForm.getMerchantId());
        productInfo.setProductInventory(productForm.getProductInventory());
        productInfo.setProductDiscounts(productForm.getProductDiscounts());
        productInfo.setProductTypeId(productForm.getProductTypeId());
        productInfo.setProductPictureUrl(productForm.getProductPictureUrl());
        productInfo.setCreatetime(new Date());
        productInfo.setState(0);
        productInfoMapper.saveProductInfo(productInfo);
    }

    public List<ProductInfo> listProductInfo(int shopid){
        ProductInfoVo productInfoVo = new ProductInfoVo();
        if(shopid != -1){
            ProductInfoCondition productInfoCondition = new ProductInfoCondition();
            productInfoCondition.setShopId(shopid);
            productInfoCondition.setState(1);
            productInfoVo.setProductInfoCondition(productInfoCondition);
        }

        return productInfoMapper.listProductInfo(productInfoVo);
    }

    public void updateProductInfo(ProductInfo productInfo){
        productInfo.setUpdatetime(new Date());
        if(productInfo.getState()==1||productInfo.getState()==2){
            productInfo.setApprovetime(new Date());
        }
        productInfoMapper.updateProductInfo(productInfo);
    }


    public ProductInfo findProductById(int id){
        return productInfoMapper.findProductById(id);
    }
}
