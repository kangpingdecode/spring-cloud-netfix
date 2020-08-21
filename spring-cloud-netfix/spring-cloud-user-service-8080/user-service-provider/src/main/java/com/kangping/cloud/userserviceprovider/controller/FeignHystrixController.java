package com.kangping.cloud.userserviceprovider.controller;

import com.kangping.springcloud.order.client.OrderServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能：
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: FeignHystrixController
 * @date 2020/8/20
 */
@RestController
public class FeignHystrixController {

    @Autowired
    private OrderServiceFeign orderServiceFeign;

    @GetMapping("/hystrix/feign/getOrder")
    public String getOrder(Integer num) {
        return orderServiceFeign.getOrder();
    }

    @GetMapping("/hystrix/feign/addOrder")
    public String addOrder(Integer num) {
        return orderServiceFeign.addOrder();
    }
}
