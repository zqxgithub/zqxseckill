package com.zqx.merchant.controller;

import com.zqx.commom.entity.MerChantInfo;
import com.zqx.commom.form.MerchantCheckInForm;
import com.zqx.merchant.service.MerchantService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("merchantControl")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @RequestMapping(value = "findMerchantbyId",method = RequestMethod.GET)
    public String findMerchantbyId(int id, Model model){
        MerChantInfo merchantInfo = merchantService.findMerchantByid(id);
        model.addAttribute("merchantInfo",merchantInfo);
        return "viewMerchant";
    }

    @RequestMapping(value = "toCheckIn",method = RequestMethod.GET)
    public String toCheckIn(){

        return "toCheckIn";
    }


    @RequestMapping(value = "checkIn",method = RequestMethod.POST)
    public String registerUser(MerchantCheckInForm merchantCheckInForm, Model model){
        if(org.apache.commons.lang.StringUtils.isBlank(merchantCheckInForm.getAccount())){
            model.addAttribute("error","商家账号不能为空");
            return "toCheckIn";
        }
        if(org.apache.commons.lang.StringUtils.isBlank(merchantCheckInForm.getPassword())|| org.apache.commons.lang.StringUtils.isBlank(merchantCheckInForm.getRepassword()) ){
            model.addAttribute("error","密码不能为空");
            return "toCheckIn";
        }
        if(!merchantCheckInForm.getPassword().equals(merchantCheckInForm.getRepassword())){
            model.addAttribute("error","两次密码输入不一致");
            return "toCheckIn";
        }
        try {
            merchantService.saveMerchantInfo(merchantCheckInForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "viewMerchant";
    }


    @RequestMapping(value = "toLogin",method = RequestMethod.GET)
    public String toLogin(){

        return "toLogin";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(MerchantCheckInForm merchantCheckInForm,Model model){
        if(org.apache.commons.lang.StringUtils.isBlank(merchantCheckInForm.getAccount())){
            model.addAttribute("error","商家用户名不能为空");
            return "toLogin";
        }
        if(StringUtils.isBlank(merchantCheckInForm.getPassword()) ){
            model.addAttribute("error","密码不能为空");
            return "toLogin";
        }
        try {
            boolean result = merchantService.verifyMerchantAccount(merchantCheckInForm);
            if(!result){
                model.addAttribute("error","用户名或者密码不对");
                return "toLogin";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "viewMerchant";
    }

}
