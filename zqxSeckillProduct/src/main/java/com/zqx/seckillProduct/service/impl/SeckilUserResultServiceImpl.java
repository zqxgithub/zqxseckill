package com.zqx.seckillProduct.service.impl;


import com.zqx.commom.entity.SeckilUserResult;
import com.zqx.seckillProduct.mapper.SeckilUserResultMapper;
import com.zqx.seckillProduct.service.SeckilUserResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckilUserResultServiceImpl implements SeckilUserResultService {

    @Autowired
    private SeckilUserResultMapper seckilUserResultMapper;

    public void saveSeckilUserResult(SeckilUserResult seckilUserResult){
        seckilUserResultMapper.saveSeckilUserResult(seckilUserResult);
    }
}
