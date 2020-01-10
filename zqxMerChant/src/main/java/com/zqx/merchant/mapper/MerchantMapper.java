package com.zqx.merchant.mapper;

import com.zqx.commom.entity.MerChantInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchantMapper {
    public MerChantInfo findMerchantInfoByid(int merchantid);

    public void saveMerchantInfo(MerChantInfo merchantfo);

    public MerChantInfo findMerchantInfoAccount(String account);
}
