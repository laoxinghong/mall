package com.jitgur.mall.portal.config;

import com.jitgur.mall.portal.domain.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * RabbitMQ实现延迟消息的方式有：死信队列、延迟插件
 * 配置交换机、队列以及交换机与队列的绑定关系
 * Created by jitgur on 2023
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 延迟消息队列绑定的交换机
     */
    @Bean
    DirectExchange orderTtlDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_TTL_CANCEL.getExchange())
                .durable(true)
                .build();
    }


    /**
     * 订单取消队列绑定的交换机
     */
    @Bean
    DirectExchange orderDirect() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }


    /**
     * 订单取消队列
     */
    @Bean
    public Queue orderCancel() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
    }


    /**
     * 延迟消息队列
     * 消息到期后转发到交换机，再由交换机根据路由键转发到订单取消队列
     */
    @Bean
    public Queue orderTtlCancel() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_ORDER_TTL_CANCEL.getName())
                // 到期后转发到的交换机
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                // 到期后转发到的路由键
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())
                .build();
    }


    /**
     * 绑定订单取消队列
     */
    @Bean
    Binding orderCancelBinding(DirectExchange orderDirect, Queue orderCancel) {
        return BindingBuilder
                .bind(orderCancel)
                .to(orderDirect)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }


    /**
     * 绑定延迟消息队列
     */
    @Bean
    Binding orderTtlCancelBinding(DirectExchange orderTtlDirect, Queue orderTtlCancel) {
        return BindingBuilder
                .bind(orderTtlCancel)
                .to(orderTtlDirect)
                .with(QueueEnum.QUEUE_ORDER_TTL_CANCEL.getRouteKey());
    }

}
