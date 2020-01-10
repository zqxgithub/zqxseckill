package com.zqx.registerCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ZqxRegisterCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqxRegisterCenterApplication.class, args);
    }

}