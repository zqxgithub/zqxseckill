package com.zqx.seckillProduct.controller;

import com.zqx.commom.entity.ProductInfo;
import com.zqx.commom.entity.SeckillProductInfo;
import com.zqx.commom.form.SeckillForm;
import com.zqx.seckillProduct.service.ProductService;
import com.zqx.seckillProduct.service.SeckillProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("seckillProductControl")
public class SeckillProductControll {

    @Autowired
    ProductService productService;

    @Autowired
    SeckillProductService seckillProductService;

    @GetMapping("listShopProduct")
    public String listShopProduct(int shopid, Model model){
        List<ProductInfo> productInfos = productService.listProductby(shopid);//这边的调用就会实现rpc的远程调用
        model.addAttribute("list",productInfos);
        return "listproduct";
    }

    @GetMapping("preSaveSeckillProduct")
    public String preSaveSeckillProduct(int id,Model model){
        ProductInfo product = productService.findProductById(id);
        model.addAttribute("productInfo",product);
        return "preSaveSeckill";
    }

    @PostMapping("saveSeckillProduct")
    public String saveSeckillProduct(SeckillForm seckillForm,Model model){
        seckillProductService.saveSeckillProduct(seckillForm);
        return "seckillSaveSuccess";
    }

    @GetMapping("listSeckillProductAll")
    public String listSeckillProductAll(int shopId,Model model){
        SeckillForm seckillForm = new SeckillForm();
        seckillForm.setShopId(shopId);
        List<SeckillProductInfo> productInfos = seckillProductService.listSeckillInfo(seckillForm);
        model.addAttribute("list",productInfos);
        return "listSeckillProductInfo";
    }

    @GetMapping("updateState")
    public String updateState(int id,int state,Model model){
        seckillProductService.updateSeckillInfoState(id,state);
        return "seckillUpdateSuccess";
    }
}
