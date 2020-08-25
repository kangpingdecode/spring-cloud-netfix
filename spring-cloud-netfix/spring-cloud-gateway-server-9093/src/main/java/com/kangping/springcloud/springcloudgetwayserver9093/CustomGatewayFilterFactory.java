package com.kangping.springcloud.springcloudgetwayserver9093;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 功能：
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: CustomGatewayFilterFactory
 * @date 2020/8/25
 */
@Component
public class CustomGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomGatewayFilterFactory.Config> {

    public static final Logger log = LoggerFactory.getLogger(CustomGatewayFilterFactory.class);

    /**
     * Parts key.
     */
    public static final String NAME = "name";

    public CustomGatewayFilterFactory() {
        super(CustomGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange,chain) ->{
            // 请求前 TODO
            log.info("[pre] filter request.name={}",config.getName());

            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                //TODO
                log.info("[post] filter request");
            }));

        };
    }


    public static class Config {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
