package com.jitgur.mall.portal.service;

import com.jitgur.mall.mbg.model.OmsCartItem;
import com.jitgur.mall.portal.domain.OmsCartPromotionItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 购物车管理service
 * Created by jitgur on 20230216
 */
public interface OmsCartItemService {

    /**
     * 新增购物车项目
     */
    @Transactional
    int add(OmsCartItem cartItem);


    /**
     * 根据用户id获取购物车列表
     */
    List<OmsCartItem> list(Long memberId);


    /**
     * 返回包含促销信息的购物车项目
     */
    List<OmsCartPromotionItem> listPromotion(Long memberId, List<Long> cartItemIdList);

}
