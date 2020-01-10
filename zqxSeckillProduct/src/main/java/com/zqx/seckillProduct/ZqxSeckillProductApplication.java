package com.zqx.seckillProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients //开启feign客户端功能
public class ZqxSeckillProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqxSeckillProductApplication.class, args);
    }

}