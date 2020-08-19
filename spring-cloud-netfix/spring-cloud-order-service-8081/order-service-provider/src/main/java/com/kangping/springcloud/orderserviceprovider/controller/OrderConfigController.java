package com.kangping.springcloud.orderserviceprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
 * @ClassName: OrderConfigController
 * @date 2020/8/19
 */
@RestController
@RefreshScope
public class OrderConfigController {

    @Value("${env}")
    private String txt;


    @GetMapping("/config")
    public String getTxt(){
        return txt;
    }



}
