package com.jitgur.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.jitgur.mall.mbg.mapper.OmsCartItemMapper;
import com.jitgur.mall.mbg.model.OmsCartItem;
import com.jitgur.mall.mbg.model.OmsCartItemExample;
import com.jitgur.mall.mbg.model.UmsMember;
import com.jitgur.mall.portal.domain.OmsCartPromotionItem;
import com.jitgur.mall.portal.service.OmsCartItemService;
import com.jitgur.mall.portal.service.OmsCartPromotionService;
import com.jitgur.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 购物车管理service实现类
 * Created by jitgur on 20230216
 */
@Service
public class OmsCartItemServiceImpl implements OmsCartItemService {

    @Autowired
    private OmsCartItemMapper cartItemMapper;
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private OmsCartPromotionService cartPromotionService;


    @Override
    public int add(OmsCartItem cartItem) {
        UmsMember currentMember = memberService.getCurrentMember();
        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());
        cartItem.setDeleteStatus(0);
        cartItem.setCreateTime(new Date());
        OmsCartItem cartItem1 = getItem(cartItem);
        if (cartItem1 == null) {
            return cartItemMapper.insert(cartItem);
        } else {
            cartItem1.setQuantity(cartItem.getQuantity() + cartItem1.getQuantity());
            cartItem1.setModifyTime(new Date());
            return cartItemMapper.updateByPrimaryKey(cartItem1);
        }
    }


    /**
     * 根据会员、商品、商品规格获取指定购物车项目
     */
    public OmsCartItem getItem(OmsCartItem cartItem) {
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria()
                .andMemberIdEqualTo(cartItem.getMemberId())
                .andProductIdEqualTo(cartItem.getProductId())
                .andProductSkuIdEqualTo(cartItem.getProductSkuId())
                .andDeleteStatusEqualTo(0);
        List<OmsCartItem> cartItemList = cartItemMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;
    }


    @Override
    public List<OmsCartItem> list(Long memberId) {
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria()
                .andDeleteStatusEqualTo(0)
                .andMemberIdEqualTo(memberId);
        return cartItemMapper.selectByExample(example);
    }


    @Override
    public List<OmsCartPromotionItem> listPromotion(Long memberId, List<Long> cartItemIdList) {
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria()
                .andIdIn(cartItemIdList)
                .andMemberIdEqualTo(memberId)
                .andDeleteStatusEqualTo(0);
        List<OmsCartItem> cartItemList = cartItemMapper.selectByExample(example);
        if(CollUtil.isNotEmpty(cartItemList)){
            return cartPromotionService.calcPromotion(cartItemList);
        }
        return null;
    }


}
