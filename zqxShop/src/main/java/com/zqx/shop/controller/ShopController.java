package com.zqx.shop.controller;

import com.zqx.commom.entity.ShopInfo;
import com.zqx.commom.entity.UserInfo;
import com.zqx.commom.form.ShopApplyForm;
import com.zqx.commom.form.ShopSearchForm;
import com.zqx.commom.form.UserRegisterForm;
import com.zqx.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("shopControl")
public class ShopController {

    @Autowired
    ShopService shopService;


    //申请店铺
    @GetMapping(value = "toApplyShop")
    public String toApplyShop(){
        return "toApplyShop";
    }

    @PostMapping("applyShop")
    public String applyShop(ShopApplyForm shopApplyForm,Model model){
        if (StringUtils.isEmpty(shopApplyForm.getShopName())){
            model.addAttribute("error","shopName null error");
            return "toApplyShop";
        }
        if(org.apache.commons.lang.StringUtils.isBlank(shopApplyForm.getShopBusinessScope())){
            model.addAttribute("error","店铺经营范围不能为空");
            return "toApplyShop";
        }
        if(org.apache.commons.lang.StringUtils.isBlank(shopApplyForm.getBusinessLicense())){
            model.addAttribute("error","店铺营业执照不能为空");
            return "toApplyShop";
        }
        try {
            shopService.saveShopInfo(shopApplyForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "viewMerchant";
    }

    //管理页面
    @GetMapping("toSearchShop")
    public String toSearchShop(){
        return "toSearchShop";
    }

    //管理员查询所有的店铺
    @PostMapping(value = "searchShop")
    public String searchShop(ShopSearchForm shopSearchForm, Model model){
        List<ShopInfo> listShop = shopService.listShopInfoBy(shopSearchForm);
        model.addAttribute("listShop",listShop);
        return "listShop";
    }

    //管理员更新店铺
    @GetMapping("updateState")
    public String updateState(int state,int id){
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setId(id);
        shopInfo.setState(state);
        shopService.updateInfoBy(shopInfo);
        return "listShop";
    }


}
