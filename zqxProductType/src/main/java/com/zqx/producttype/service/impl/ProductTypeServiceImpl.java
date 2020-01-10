package com.zqx.producttype.service.impl;

import com.zqx.commom.entity.ProductTypeInfo;
import com.zqx.producttype.mapper.ProductTypeMapper;
import com.zqx.producttype.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    public void saveProductType(ProductTypeInfo productTypeInfo){
        productTypeMapper.saveProductType(productTypeInfo);
    }
}
