package com.zqx.merchant.service;

import com.zqx.commom.entity.MerChantInfo;
import com.zqx.commom.form.MerchantCheckInForm;

public interface MerchantService {

    public MerChantInfo findMerchantByid(int merchantid);

    public void saveMerchantInfo(MerchantCheckInForm merchantCheckInForm) throws Exception;

    public boolean verifyMerchantAccount(MerchantCheckInForm merchantCheckInForm) throws Exception;
}
