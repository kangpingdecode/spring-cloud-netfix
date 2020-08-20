package com.kangping.springcloud.orderserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceProviderApplication.class, args);
    }

}
