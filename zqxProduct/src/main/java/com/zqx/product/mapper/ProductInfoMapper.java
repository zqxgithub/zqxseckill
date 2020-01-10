package com.zqx.product.mapper;

import com.zqx.commom.entity.ProductInfo;
import com.zqx.commom.vo.ProductInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductInfoMapper {
    public void saveProductInfo(ProductInfo productInfo);

    List<ProductInfo> listProductInfo(ProductInfoVo productInfoVo);

    void updateProductInfo(ProductInfo productInfo);

    public ProductInfo findProductById(int id);
}
