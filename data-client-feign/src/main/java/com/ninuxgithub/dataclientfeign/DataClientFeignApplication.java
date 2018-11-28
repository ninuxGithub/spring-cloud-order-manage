package com.ninuxgithub.dataclientfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
//参考： https://blog.csdn.net/ytyDaMoTou/article/details/80180105
public class DataClientFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataClientFeignApplication.class, args);
    }
}
