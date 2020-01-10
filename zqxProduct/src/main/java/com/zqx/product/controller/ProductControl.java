package com.zqx.product.controller;

import com.zqx.commom.entity.ProductDetail;
import com.zqx.commom.entity.ProductInfo;
import com.zqx.commom.form.ProductForm;
import com.zqx.product.service.ProductDetailService;
import com.zqx.product.service.ProductInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("productControl")
public class ProductControl {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductDetailService productDetailService;

    @RequestMapping(value = "toApplyProduct",method = RequestMethod.GET)
    public String toApplyProduct(){
        return "toApplyProduct";
    }

    @RequestMapping(value = "toAddProductDetail",method = RequestMethod.GET)
    public String toAddProductDetail(Model model,String productId){
        ProductInfo productInfo = productInfoService.findProductById(Integer.valueOf(productId));
        model.addAttribute("productInfo",productInfo);
        return "toAddProductDetail";
    }


    @RequestMapping(value = "applyProduct",method = RequestMethod.POST)
    public String applyProduct(ProductForm productForm, Model model){
        System.out.println(productForm.toString());
        if(StringUtils.isBlank(productForm.getProductName())){
            model.addAttribute("error","商品名称不能为空");
            return "toRegisterUser";
        }
        if(StringUtils.isBlank(productForm.getProductTitle())){
            model.addAttribute("error","商品标题不能为空");
            return "toRegisterUser";
        }
        if(StringUtils.isBlank(productForm.getProductPriceParam())){
            model.addAttribute("error","商品价格不能为空");
            return "toRegisterUser";
        }
        try {
            productInfoService.saveProductInfo(productForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "viewUser";
    }

    @RequestMapping(value = "addProductDetail",method = RequestMethod.POST)
    public String addProductDetail(ProductDetail productDetail, Model model){
        productDetailService.saveProductDetail(productDetail);
        return "redirect:/productControl/listProduct";
    }


    @RequestMapping(value = "listProduct",method = RequestMethod.GET)
    public String listProduct(Model model){
        try {
            List<ProductInfo> list = productInfoService.listProductInfo(-1);
            model.addAttribute("list",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "listproduct";
    }

    @RequestMapping(value = "updateState",method = RequestMethod.GET)
    public String updateState(int id ,int state,Model model){
        try {
            ProductInfo productInfo =  new ProductInfo();
            productInfo.setId(id);
            productInfo.setState(state);
            productInfoService.updateProductInfo(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "listproduct";
    }


}
