package com.kangping.springcloud.order.client;


import com.kangping.springcloud.order.api.OrderService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

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
@FeignClient(value = "order-service",fallback = OrderServiceFeign.OrderServiceHystrix.class)
//@FeignClient(value = "order-service")
public interface OrderServiceFeign extends OrderService {

    @Component
    class OrderServiceHystrix implements OrderServiceFeign{

        @Override
        public String getOrder() {
            return "openfeign降级了---getOrder";
        }

        @Override
        public String addOrder() {
            return "openfeign降级了---addOrder";
        }
    }
}
