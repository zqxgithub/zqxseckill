package com.zqx.userModule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan(basePackages = "com.zqx.userModule.mapper")
@EnableEurekaClient // 声明这是一个Eureka Client,是一个服务提供者，注册到eureka中心
public class ZqxUserModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqxUserModuleApplication.class, args);
    }

}