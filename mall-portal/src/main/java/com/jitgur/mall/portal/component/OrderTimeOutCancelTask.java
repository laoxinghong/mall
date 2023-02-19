package com.jitgur.mall.portal.component;

import com.jitgur.mall.portal.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 取消超时订单定时任务
 * Created by jitgur on 20230219
 */
@Component
public class OrderTimeOutCancelTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);
    @Autowired
    private OmsPortalOrderService orderService;


    /**
     * cron表达式：seconds minutes hours dayOfMonth month dayOfWeek [year]
     * 每十分钟扫描一次
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
    public void cancelTimeOutOrder() {
        int count = orderService.cancelTimeOutOrder();
        LOGGER.info("cancel time out order and the quantity :{}", count);
    }

}
