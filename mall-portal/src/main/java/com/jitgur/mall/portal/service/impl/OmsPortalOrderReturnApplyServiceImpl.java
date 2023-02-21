package com.jitgur.mall.portal.service.impl;

import com.jitgur.mall.common.exception.Asserts;
import com.jitgur.mall.mbg.mapper.OmsOrderMapper;
import com.jitgur.mall.mbg.mapper.OmsOrderReturnApplyMapper;
import com.jitgur.mall.mbg.model.OmsOrder;
import com.jitgur.mall.mbg.model.OmsOrderReturnApply;
import com.jitgur.mall.portal.domain.OmsPortalOrderReturnApplyParam;
import com.jitgur.mall.portal.service.OmsPortalOrderReturnApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 退货申请管理service实现类
 * Created by jitgur on 20230217
 */
@Service
public class OmsPortalOrderReturnApplyServiceImpl implements OmsPortalOrderReturnApplyService {

    @Autowired
    private OmsOrderReturnApplyMapper applyMapper;
    @Autowired
    private OmsOrderMapper orderMapper;


    @Override
    public int create(OmsPortalOrderReturnApplyParam applyParam) {
        OmsOrder order = orderMapper.selectByPrimaryKey(applyParam.getOrderId());
        if (order == null) {
            return 0;
        }
        if (order.getDeleteStatus() == 1 || (order.getStatus() != 1 && order.getStatus() != 2 && order.getStatus() != 3)) {
            Asserts.fail("当前订单不能申请退款");
        }
        order.setStatus(5);
        orderMapper.updateByPrimaryKeySelective(order);
        OmsOrderReturnApply orderReturnApply = new OmsOrderReturnApply();
        BeanUtils.copyProperties(applyParam, orderReturnApply);
        orderReturnApply.setCreateTime(new Date());
        orderReturnApply.setStatus(0);
        return applyMapper.insert(orderReturnApply);
    }

}
