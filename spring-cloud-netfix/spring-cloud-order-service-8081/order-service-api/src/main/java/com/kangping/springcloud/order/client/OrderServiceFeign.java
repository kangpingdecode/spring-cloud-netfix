package com.kangping.springcloud.order.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 功能：
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: IOrderService
 * @date 2020/7/6
 */
@FeignClient("order")
public interface OrderServiceFeign {

    @GetMapping("/getOrder")
    String getOrder();
}
