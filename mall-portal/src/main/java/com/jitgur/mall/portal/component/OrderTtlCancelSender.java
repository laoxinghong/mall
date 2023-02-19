package com.jitgur.mall.portal.component;

import com.jitgur.mall.portal.domain.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 延迟取消订单消息发送者
 * Created by jitgur on 20230219
 */
@Component
public class OrderTtlCancelSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTtlCancelSender.class);
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Long orderId, final long delayTimes) {
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_TTL_CANCEL.getExchange(),
                QueueEnum.QUEUE_ORDER_TTL_CANCEL.getRouteKey(), orderId,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        // 设置延迟消息延迟毫秒数
                        message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                        return message;
                    }
                });
        LOGGER.info("send delay message orderId:{}", orderId);
    }

}
