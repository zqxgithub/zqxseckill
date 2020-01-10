package com.zqx.producttype.controller;

import com.zqx.commom.entity.ProductTypeInfo;
import com.zqx.producttype.service.ProductTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("productTypeControl")
public class ProductTypeControl {

    @Autowired
    private ProductTypeService productTypeService;

    //添加商品类别页面
    @GetMapping(value = "toAddProductTypeInfo")
    public String toAddProductTypeInfo(){
        return "toAddProductTypeInfo";
    }


    //添加商品类别
    @PostMapping(value = "addProductTypeInfo")
    public String addProductTypeInfo(ProductTypeInfo productTypeInfo, Model model){
        if(StringUtils.isBlank(productTypeInfo.getProducttypeName())){
            model.addAttribute("error","商品类别名称不能为空");
            return "toAddProductTypeInfo";
        }
        try {
            productTypeService.saveProductType(productTypeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "AddProductTypeInfoSuccess";
    }

}
