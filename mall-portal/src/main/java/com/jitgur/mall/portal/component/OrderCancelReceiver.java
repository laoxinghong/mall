package com.jitgur.mall.portal.component;

import com.jitgur.mall.portal.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 订单取消消息接收者
 * Created by jitgur on 20230219
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class OrderCancelReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCancelReceiver.class);
    @Autowired
    private OmsPortalOrderService orderService;

    @RabbitHandler
    public void handle(Long orderId) {
        orderService.cancelOrder(orderId);
        LOGGER.info("process order:{}", orderId);
    }

}
