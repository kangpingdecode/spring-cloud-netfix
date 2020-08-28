package com.kangping.springcloud.springcloudgetwayserver9093;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 功能： 基于redis存储路由信息
 * </p>
 *
 * @author kangping
 * Copyright Inc. All rights reserved
 * @version v1.0
 * @ClassName: RedisRouteDefinitionRepository
 * @date 2020/8/28
 */

@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String GATEWAY_ROUTE_KEY = "gateway_dynamic_route";

    /**
     * <p >
     * 功能： 调刷新端点，会调用此接口
     * </p>
     *  
     * @return
     * @author kangping
     * @date  2020/8/28 17:40
     */
    
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();
        redisTemplate.opsForHash().values(GATEWAY_ROUTE_KEY).stream().forEach(e->{
            routeDefinitions.add(JSON.parseObject(e.toString(),RouteDefinition.class));
        });
        return Flux.fromIterable(routeDefinitions);
    }

    /**
     * <p >
     * 功能： 保存
     * </p>
     * 
     * @param route 
     * @return
     * @author kangping
     * @date  2020/8/28 17:39
     */
    
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
       return route.flatMap(routeDefinition ->{
            redisTemplate.opsForHash().put(GATEWAY_ROUTE_KEY,routeDefinition.getId(), JSON.toJSONString(routeDefinition));
            return Mono.empty();
        });
    }

    /**
     * <p >
     * 功能：删除
     * </p>
     * 
     * @param routeId 
     * @return
     * @author kangping
     * @date  2020/8/28 17:40
     */
    
    @Override
    public Mono<Void> delete(Mono<String> routeId) {

        return routeId.flatMap(id ->{
            if (redisTemplate.opsForHash().hasKey(GATEWAY_ROUTE_KEY, id)) {
                redisTemplate.opsForHash().delete(GATEWAY_ROUTE_KEY, id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new Exception("路由不存在")));
        });
    }

}
