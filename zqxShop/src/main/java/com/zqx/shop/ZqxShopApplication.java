package com.zqx.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient // 声明这是一个Eureka Client,是一个服务提供者，注册到eureka中心
public class  ZqxShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqxShopApplication.class, args);
    }

}