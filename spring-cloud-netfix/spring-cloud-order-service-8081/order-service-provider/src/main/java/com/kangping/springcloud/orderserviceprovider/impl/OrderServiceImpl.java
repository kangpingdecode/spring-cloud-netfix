package com.kangping.springcloud.orderserviceprovider.impl;


import com.kangping.springcloud.order.client.OrderServiceFeign;
import org.springframework.web.bind.annotation.RestController;;
/**
 * <p>
 * 功能：
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: OrderServiceImpl
 * @date 2020/8/19
 */
@RestController
public class OrderServiceImpl implements OrderServiceFeign {

    public String getOrder() {
        return "RETURN ORDER";
    }
}
