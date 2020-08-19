package com.kangping.cloud.userserviceprovider.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;


    /**
     *  熔断降级
     *  默认：在10秒内，请求大于等于20次，失败率大于等于百分之50，熔断5秒
     *  配置：在10秒内，请求大于等于5次，失败率大于等于百分之50，熔断5秒
     * @param num
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback", // 降级调用的方法
            commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 开启熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), // 请求大于等于5
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"), // 熔断5秒
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50") // 失败比例
    })
    @GetMapping("/hystrix/getOrder")
    public String getOrder(Integer num) {
        if (num % 2 == 0) {
            return "请求成功！";
        }
        return restTemplate.getForObject("http://localhost:8081/getOrder",String.class);
    }


    public String fallback(Integer num) {
        return "熔断降级";
    }

    /**
     *  超时降级
     * @param num
     * @return
     */
    @HystrixCommand(fallbackMethod = "timeoutFallback", // 降级调用的方法
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"), // 开启熔断
            })
    @GetMapping("/hystrix/getOrder2")
    public String getOrder2(Integer num) {
        return restTemplate.getForObject("http://localhost:8081/getOrder",String.class);
    }


    public String timeoutFallback(Integer num) {
        return "超时降级降级";
    }
}
