package com.jitgur.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.jitgur.mall.common.exception.Asserts;
import com.jitgur.mall.mbg.mapper.*;
import com.jitgur.mall.mbg.model.*;
import com.jitgur.mall.portal.dao.SmsCouponHistoryDao;
import com.jitgur.mall.portal.service.UmsMemberCouponService;
import com.jitgur.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 会员优惠券管理Service实现类
 * Created by jitgur on 20230212
 */
@Service
public class UmsMemberCouponServiceImpl implements UmsMemberCouponService {

    @Autowired
    private SmsCouponHistoryMapper couponHistoryMapper;
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private SmsCouponMapper couponMapper;
    @Autowired
    private SmsCouponProductRelationMapper couponProductRelationMapper;
    @Autowired
    private SmsCouponProductCategoryRelationMapper couponProductCategoryRelationMapper;
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private SmsCouponHistoryDao couponHistoryDao;


    @Override
    public void add(Long couponId) {
        UmsMember currentMember = memberService.getCurrentMember();
        SmsCoupon coupon = couponMapper.selectByPrimaryKey(couponId);
        Date now = new Date();

        if (coupon == null) {
            Asserts.fail("当前优惠券不存在");
        }
        if (coupon.getCount() <= 0) {
            Asserts.fail("当前优惠券已被领完");
        }
        if (now.before(coupon.getEnableTime())) {
            Asserts.fail("还未到领取时间");
        }
        if (coupon.getMemberLevel() != 0) {
            if (coupon.getMemberLevel() > currentMember.getMemberLevelId()) {
                Asserts.fail("当前等级不能领取该优惠券");
            }
        }

        SmsCouponHistoryExample couponHistoryExample = new SmsCouponHistoryExample();
        couponHistoryExample.createCriteria().andMemberIdEqualTo(currentMember.getId()).andCouponIdEqualTo(couponId);
        long count = couponHistoryMapper.countByExample(couponHistoryExample);
        if (count >= coupon.getPerLimit()) {
            Asserts.fail("领取数量已达上限");
        }

        // 添加领取记录
        SmsCouponHistory couponHistory = new SmsCouponHistory();
        couponHistory.setCouponId(couponId);
        couponHistory.setMemberId(currentMember.getId());
        couponHistory.setCouponCode(generateCouponCode(currentMember.getId()));
        couponHistory.setMemberNickname(currentMember.getNickname());
        couponHistory.setCreateTime(now);
        couponHistory.setGetType(1);
        couponHistory.setUseStatus(0);
        couponHistoryMapper.insert(couponHistory);

        // 更新优惠券数量
        coupon.setCount(coupon.getCount() - 1);
        coupon.setReceiveCount(coupon.getReceiveCount() == null ? 1 : coupon.getReceiveCount() + 1);
        couponMapper.updateByPrimaryKey(coupon);
    }


    /**
     * 生成优惠券码：时间戳后8位+4位随机数+用户id后4位
     */
    private String generateCouponCode(Long memberId) {
        StringBuilder sb = new StringBuilder();

        // 时间戳
        long currentTimeMillis = System.currentTimeMillis();
        String timeStr = Long.toString(currentTimeMillis);
        sb.append(timeStr.substring(timeStr.length() - 8));

        // 随机数
        for (int i = 0; i < 4; i++) {
            sb.append(new Random().nextInt(10));
        }

        // 用户id后4位
        String memberIdStr = memberId.toString();
        if (memberIdStr.length() <= 4) {
            sb.append(String.format("%04d", memberId));
        } else {
            sb.append(memberIdStr.substring(memberIdStr.length() - 4));
        }

        return sb.toString();
    }


    @Override
    public List<SmsCouponHistory> listHistory(Integer useStatus) {
        UmsMember currentMember = memberService.getCurrentMember();
        SmsCouponHistoryExample example = new SmsCouponHistoryExample();
        SmsCouponHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(currentMember.getId());
        if (useStatus != null) {
            criteria.andUseStatusEqualTo(useStatus);
        }
        return couponHistoryMapper.selectByExample(example);
    }


    @Override
    public List<SmsCoupon> listByProduct(Long productId) {
        List<Long> allCouponIdList = new ArrayList<>();

        // 商品相关
        SmsCouponProductRelationExample cprExample = new SmsCouponProductRelationExample();
        cprExample.createCriteria().andProductIdEqualTo(productId);
        List<SmsCouponProductRelation> cpList = couponProductRelationMapper.selectByExample(cprExample);
        if (CollUtil.isNotEmpty(cpList)) {
            List<Long> ids = cpList.stream().map(SmsCouponProductRelation::getCouponId).collect(Collectors.toList());
            allCouponIdList.addAll(ids);
        }

        // 商品分类相关
        PmsProduct product = productMapper.selectByPrimaryKey(productId);
        SmsCouponProductCategoryRelationExample cpcExample = new SmsCouponProductCategoryRelationExample();
        cpcExample.createCriteria().andProductCategoryIdEqualTo(product.getProductCategoryId());
        List<SmsCouponProductCategoryRelation> cpcRelationList = couponProductCategoryRelationMapper.selectByExample(cpcExample);
        if (CollUtil.isNotEmpty(cpcRelationList)) {
            List<Long> ids = cpcRelationList.stream().map(SmsCouponProductCategoryRelation::getCouponId).collect(Collectors.toList());
            allCouponIdList.addAll(ids);
        }

        // 全场通用
        SmsCouponExample couponExample = new SmsCouponExample();
        couponExample.createCriteria()
                .andUseTypeEqualTo(0)
                .andStartTimeLessThan(new Date())
                .andEndTimeGreaterThan(new Date());
        // 过滤掉已经过期的优惠券
        if (CollUtil.isNotEmpty(allCouponIdList)) {
            couponExample.or(couponExample.createCriteria()
                    .andStartTimeLessThan(new Date())
                    .andEndTimeGreaterThan(new Date())
                    .andIdIn(allCouponIdList));
        }
        List<SmsCoupon> couponList = couponMapper.selectByExample(couponExample);
        if (CollUtil.isNotEmpty(couponList)) {
            // 返回用户拥有的优惠券
            List<SmsCouponHistory> couponHistoryList = listHistory(0);
            List<Long> ownCouponIdList = couponHistoryList.stream().map(SmsCouponHistory::getCouponId).collect(Collectors.toList());
            return couponList.stream().filter(coupon -> !ownCouponIdList.contains(coupon.getId())).collect(Collectors.toList());
        }

        return null;
    }


    @Override
    public List<SmsCoupon> list(Integer useStatus) {
        UmsMember currentMember = memberService.getCurrentMember();
        return couponHistoryDao.getCouponList(currentMember.getId(), useStatus);
    }


}
