package com.jitgur.mall.portal.service;

import com.jitgur.mall.mbg.model.CmsSubject;
import com.jitgur.mall.mbg.model.PmsProduct;
import com.jitgur.mall.mbg.model.SmsFlashPromotionSession;
import com.jitgur.mall.portal.domain.SmsHomeContentDetail;

import java.util.List;

/**
 * 首页内容管理service
 * Created by jitgur on 20230214
 */
public interface SmsHomeService {

    /**
     * 获取首页内容
     */
    SmsHomeContentDetail content();


    /**
     * 分页获取首页人气推荐商品列表
     */
    List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize);


    /**
     * 分页获取首页新品推荐商品列表
     */
    List<PmsProduct> newProductList(Integer pageNum, Integer pageSize);


    /**
     * 分页获取专题列表
     */
    List<CmsSubject> getSubjectList(Long subjectCateId, Integer pageNum, Integer pageSize);


    /**
     * 获取秒杀活动全部场次信息
     */
    List<SmsFlashPromotionSession> getFlashPromotionSessionList(Long flashPromotionId);

}
