package com.zqx.product.service.impl;

import com.zqx.commom.entity.ProductDetail;
import com.zqx.product.mapper.ProductDetailMapper;
import com.zqx.product.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailMapper productDetailMapper;

    public void saveProductDetail(ProductDetail productDetail){
        productDetailMapper.saveProductDetail(productDetail);
    }
}
