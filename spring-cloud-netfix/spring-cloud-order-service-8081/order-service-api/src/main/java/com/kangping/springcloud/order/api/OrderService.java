package com.kangping.springcloud.order.api;


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
public interface OrderService {

    @GetMapping("/getOrder")
    String getOrder();
}
