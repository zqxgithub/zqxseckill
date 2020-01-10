package com.zqx.seckillProduct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("aoptest")
    public String aoptest(){
        return "aop success";
    }
}
