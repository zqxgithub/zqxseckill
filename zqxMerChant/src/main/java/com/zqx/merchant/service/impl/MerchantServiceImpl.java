package com.zqx.merchant.service.impl;

import com.zqx.commom.entity.MerChantInfo;
import com.zqx.commom.form.MerchantCheckInForm;
import com.zqx.commom.utils.Md5Util;
import com.zqx.merchant.mapper.MerchantMapper;
import com.zqx.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    public MerChantInfo findMerchantByid(int merchantid) {
        return merchantMapper.findMerchantInfoByid(merchantid);
    }

    //添加商户
    public void saveMerchantInfo(MerchantCheckInForm merchantCheckInForm) throws Exception {
        MerChantInfo merchantinfo = new MerChantInfo();
        merchantinfo.setCreatetime(new Date());
        merchantinfo.setAccount(merchantCheckInForm.getAccount());
        merchantinfo.setTelphone(merchantCheckInForm.getTelphone());
        merchantinfo.setAddress(merchantCheckInForm.getAddress());
        merchantinfo.setCity(merchantCheckInForm.getCity());
        merchantinfo.setProvince(merchantCheckInForm.getProvince());
        merchantinfo.setName(merchantCheckInForm.getName());
        String password = merchantCheckInForm.getPassword();//获取用户输入的密码
        merchantinfo.setOriginalPwd(password);//原始密码
        merchantinfo.setPassword(Md5Util.md5(password, Md5Util.slat));//加密密码
        merchantMapper.saveMerchantInfo(merchantinfo);
    }

    //验证账号
    public boolean verifyMerchantAccount(MerchantCheckInForm merchantCheckInForm) throws Exception {
        String account = merchantCheckInForm.getAccount();
        String password = merchantCheckInForm.getPassword();
        MerChantInfo merchantInfo = merchantMapper.findMerchantInfoAccount(merchantCheckInForm.getAccount());
        if (merchantInfo == null) {
            return false;
        }
        String md5Password = merchantInfo.getPassword();
        return Md5Util.verify(password, Md5Util.slat, md5Password);
    }
}
