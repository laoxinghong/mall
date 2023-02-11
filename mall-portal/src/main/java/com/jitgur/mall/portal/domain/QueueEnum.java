package com.jitgur.mall.portal.domain;

import lombok.Getter;

/**
 * 消息队列枚举
 * 用于定义延迟消息队列和订单取消队列的信息
 * Created by jitgur on 20230211
 */
@Getter
public enum QueueEnum {

    /**
     * 订单取消队列
     */
    QUEUE_ORDER_CANCEL("mall.order.direct", "mall.order.cancel", "mall.order.cancel"),

    /**
     * 延迟消息队列
     */
    QUEUE_ORDER_TTL_CANCEL("mall.order.ttl.direct", "mall.order.ttl.cancel", "mall.order.ttl.cancel");


    private final String exchange;

    private final String name;

    private final String routeKey;


    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }

}
