package com.zqx.product.controller;

import com.zqx.commom.entity.ProductInfo;
import com.zqx.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productOutControl")
public class ProductOutControl {

    @Autowired
    private ProductInfoService productInfoService;


    @PostMapping(value = "listProductby")
    public List<ProductInfo> listProductby(int shopid, Model model) {
        List<ProductInfo> list = productInfoService.listProductInfo(shopid);
        return list;
    }

    @PostMapping("findProductById")
    public ProductInfo findProductById(int id, Model model) {
        ProductInfo productInfo = productInfoService.findProductById(id);
        return productInfo;
    }
}
