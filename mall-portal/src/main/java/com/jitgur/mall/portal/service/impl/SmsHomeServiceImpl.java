package com.jitgur.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.jitgur.mall.mbg.mapper.CmsSubjectMapper;
import com.jitgur.mall.mbg.mapper.SmsFlashPromotionMapper;
import com.jitgur.mall.mbg.mapper.SmsFlashPromotionSessionMapper;
import com.jitgur.mall.mbg.mapper.SmsHomeAdvertiseMapper;
import com.jitgur.mall.mbg.model.*;
import com.jitgur.mall.portal.dao.SmsHomeDao;
import com.jitgur.mall.portal.domain.SmsHomeContentDetail;
import com.jitgur.mall.portal.domain.SmsHomeFlashPromotion;
import com.jitgur.mall.portal.service.SmsHomeService;
import com.jitgur.mall.portal.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 首页内容管理service实现类
 * Created by jitgur on 20230214
 */
public class SmsHomeServiceImpl implements SmsHomeService {

    @Autowired
    private SmsHomeDao homeDao;
    @Autowired
    private CmsSubjectMapper subjectMapper;
    @Autowired
    private SmsHomeAdvertiseMapper homeAdvertiseMapper;
    @Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;
    @Autowired
    private SmsFlashPromotionSessionMapper flashPromotionSessionMapper;


    @Override
    public SmsHomeContentDetail content() {
        SmsHomeContentDetail content = new SmsHomeContentDetail();
        content.setHomeAdvertiseList(homeAdvertiseList());
        content.setBrandList(homeDao.recommendBrandList(0, 5));
        content.setHomeFlashPromotionList(homeFlashPromotionList());
        content.setHotProductList(homeDao.hotProductList(0, 5));
        content.setNewProductList(homeDao.newProductList(0, 5));
        content.setSubjectList(homeDao.homeSubjectList(0, 5));
        return content;
    }


    @Override
    public List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize) {
        return homeDao.hotProductList((pageNum - 1) * pageSize, pageSize);
    }


    @Override
    public List<PmsProduct> newProductList(Integer pageNum, Integer pageSize) {
        return homeDao.newProductList((pageNum - 1) * pageSize, pageSize);
    }


    @Override
    public List<CmsSubject> getSubjectList(Long subjectCateId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        CmsSubjectExample example = new CmsSubjectExample();
        CmsSubjectExample.Criteria criteria = example.createCriteria();
        criteria.andShowStatusEqualTo(1);
        if (subjectCateId != null) {
            criteria.andCategoryIdEqualTo(subjectCateId);
        }
        return subjectMapper.selectByExample(example);
    }


    public List<SmsHomeAdvertise> homeAdvertiseList() {
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.createCriteria()
                .andStatusEqualTo(1)
                .andTypeEqualTo(1);
        example.setOrderByClause("sort desc");
        return homeAdvertiseMapper.selectByExample(example);
    }


    public List<SmsHomeFlashPromotion> homeFlashPromotionList() {
        List<SmsHomeFlashPromotion> homeFlashPromotionList = new ArrayList<>();

        Date now = new Date();
        // 获取正在进行中的秒杀活动
        List<SmsFlashPromotion> flashPromotionList = flashPromotionList(now);
        if (CollUtil.isNotEmpty(flashPromotionList)) {
            for (SmsFlashPromotion flashPromotion : flashPromotionList) {
                SmsHomeFlashPromotion homeFlashPromotion = new SmsHomeFlashPromotion();
                // 设置秒杀活动
                homeFlashPromotion.setFlashPromotion(flashPromotion);
                // 获取当前场次
                SmsFlashPromotionSession session = flashPromotionSession(flashPromotion.getId(), now);
                if (session != null) {
                    homeFlashPromotion.setStartTime(session.getStartTime());
                    homeFlashPromotion.setEndTime(session.getEndTime());
                    // 获取秒杀商品
                    homeFlashPromotion.setProductList(homeDao.getFlashPromotionProductList(flashPromotion.getId(), session.getId()));
                }
                // 获取下一场次
                SmsFlashPromotionSession nextSession = nextFlashPromotionSession(flashPromotion.getId(), now);
                if (nextSession != null) {
                    homeFlashPromotion.setNextStartTime(nextSession.getStartTime());
                    homeFlashPromotion.setNextEndTime(nextSession.getEndTime());
                }
                homeFlashPromotionList.add(homeFlashPromotion);
            }
        }
        return homeFlashPromotionList;
    }


    /**
     * 获取正在进行中的秒杀活动
     *
     * @param now
     */
    private List<SmsFlashPromotion> flashPromotionList(Date now) {
        Date date = DateUtil.getDate(now);
        SmsFlashPromotionExample example = new SmsFlashPromotionExample();
        example.createCriteria()
                .andStartDateLessThan(date)
                .andEndDateGreaterThan(date)
                .andStatusEqualTo(1);
        return flashPromotionMapper.selectByExample(example);
    }


    /**
     * 获取正在进行中的秒杀活动场次
     *
     * @param now
     */
    public SmsFlashPromotionSession flashPromotionSession(Long flashPromotionId, Date now) {
        Date time = DateUtil.getTime(now);
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.createCriteria()
                .andFlashPromotionIdEqualTo(flashPromotionId)
                .andStartTimeLessThan(time)
                .andEndTimeGreaterThan(time)
                .andStatusEqualTo(1);
        List<SmsFlashPromotionSession> sessionList = flashPromotionSessionMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(sessionList)) {
            return sessionList.get(0);
        }
        return null;
    }


    /**
     * 获取下一场秒杀活动场次
     */
    public SmsFlashPromotionSession nextFlashPromotionSession(Long flashPromotionId, Date now) {
        Date time = DateUtil.getTime(now);
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.createCriteria()
                .andFlashPromotionIdEqualTo(flashPromotionId)
                .andStartTimeGreaterThan(time)
                .andStatusEqualTo(1);
        example.setOrderByClause("start_time asc");
        List<SmsFlashPromotionSession> sessionList = flashPromotionSessionMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(sessionList)) {
            return sessionList.get(0);
        }
        return null;
    }


    /**
     * 获取秒杀活动全部场次信息
     */
    public List<SmsFlashPromotionSession> getFlashPromotionSessionList(Long flashPromotionId) {
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.createCriteria()
                .andFlashPromotionIdEqualTo(flashPromotionId)
                .andStatusEqualTo(1);
        example.setOrderByClause("start_time asc");
        return flashPromotionSessionMapper.selectByExample(example);
    }

}
