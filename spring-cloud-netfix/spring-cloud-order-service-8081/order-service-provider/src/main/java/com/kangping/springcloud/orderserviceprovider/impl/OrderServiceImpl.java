package com.kangping.springcloud.orderserviceprovider.impl;


import com.kangping.springcloud.order.api.OrderService;
import com.kangping.springcloud.order.client.OrderServiceFeign;
import org.springframework.stereotype.Service;
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

public class OrderServiceImpl implements OrderService {

    public String getOrder() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "RETURN getOrder";
    }

    public String addOrder() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "RETURN addOrder";
    }


}
