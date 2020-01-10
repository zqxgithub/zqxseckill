package com.zqx.seckillProduct.controller;

import com.zqx.seckillProduct.service.SeckillByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("seckillByProductControl")
public class SeckillByProductController {

    @Autowired
    private SeckillByService seckillByService;

    @GetMapping("procedureLockBy")
    public Map<String, String> procedureLockBy(int userId, int id){
        Map<String, String> map = seckillByService.procedureLockBy(userId, id);
        return map;
    }

    @GetMapping("mulitThread")
    public void mulitThread(int userId, int id){
        seckillByService.mulitThread(userId,id);
    }

    @GetMapping("pessimismLock")
    public Map<String, String> pessimismLock(int userId, int id){
        Map<String, String> map = seckillByService.pessimismLock(userId, id);
        return map;
    }

    @GetMapping("optimisticLock")
    public Map<String, String> optimisticLock(int userId, int id){
        Map<String, String> map = seckillByService.optimisticLock(userId, id);
        return map;
    }

}
