package com.zqx.product.mapper;

import com.zqx.commom.entity.ProductDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailMapper {

    public void saveProductDetail(ProductDetail productDetail);
}
