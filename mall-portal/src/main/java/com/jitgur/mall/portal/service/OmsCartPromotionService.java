package com.jitgur.mall.portal.service;

import com.jitgur.mall.mbg.model.OmsCartItem;
import com.jitgur.mall.portal.domain.OmsCartPromotionItem;

import java.util.List;

/**
 * 购物车促销管理service
 * Created by jitgur on 20230218
 */
public interface OmsCartPromotionService {

    /**
     * 计算购物车项目促销信息
     */
    List<OmsCartPromotionItem> calcPromotion(List<OmsCartItem> cartItemList);

}
