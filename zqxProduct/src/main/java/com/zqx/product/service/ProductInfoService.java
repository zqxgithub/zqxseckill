package com.zqx.product.service;

import com.zqx.commom.entity.ProductInfo;
import com.zqx.commom.form.ProductForm;

import java.util.List;

public interface ProductInfoService {
    public void saveProductInfo(ProductForm productForm);

    public List<ProductInfo> listProductInfo(int shopid);
    public void updateProductInfo(ProductInfo productInfo);
    public ProductInfo findProductById(int id);
}
