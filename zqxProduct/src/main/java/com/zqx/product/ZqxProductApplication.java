package com.zqx.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient // 声明这是一个Eureka Client,是一个服务提供者，注册到eureka中心
public class ZqxProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqxProductApplication.class, args);
    }

}