package com.zqx.producttype.mapper;


import com.zqx.commom.entity.ProductTypeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductTypeMapper {

    public void saveProductType(ProductTypeInfo productTypeInfo);

}
