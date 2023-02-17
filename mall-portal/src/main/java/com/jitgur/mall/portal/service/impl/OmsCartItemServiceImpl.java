package com.jitgur.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.jitgur.mall.mbg.mapper.OmsCartItemMapper;
import com.jitgur.mall.mbg.model.OmsCartItem;
import com.jitgur.mall.mbg.model.OmsCartItemExample;
import com.jitgur.mall.mbg.model.UmsMember;
import com.jitgur.mall.portal.dao.OmsCartItemDao;
import com.jitgur.mall.portal.domain.OmsCartProduct;
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
    @Autowired
    private OmsCartItemDao cartItemDao;


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
        if (CollUtil.isNotEmpty(cartItemList)) {
            return cartPromotionService.calcPromotion(cartItemList);
        }
        return null;
    }


    @Override
    public int updateQuantity(Long cartItemId, Long memberId, Integer quantity) {
        OmsCartItem cartItem = new OmsCartItem();
        cartItem.setQuantity(quantity);
        cartItem.setModifyTime(new Date());
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria()
                .andIdEqualTo(cartItemId)
                .andMemberIdEqualTo(memberId)
                .andDeleteStatusEqualTo(0);
        return cartItemMapper.updateByExampleSelective(cartItem, example);
    }


    @Override
    public int delete(Long memberId, List<Long> cartItemIdList) {
        OmsCartItem cartItem = new OmsCartItem();
        cartItem.setDeleteStatus(1);
        cartItem.setModifyTime(new Date());
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andIdIn(cartItemIdList);
        return cartItemMapper.updateByExampleSelective(cartItem, example);
    }


    @Override
    public OmsCartProduct cartProduct(Long productId) {
        return cartItemDao.cartProduct(productId);
    }


    @Override
    public int updateAttr(OmsCartItem cartItem) {
        OmsCartItem cartItem1 = new OmsCartItem();
        cartItem1.setId(cartItem.getId());
        cartItem1.setModifyTime(new Date());
        cartItem1.setDeleteStatus(1);
        // 设置原item为删除状态
        cartItemMapper.updateByPrimaryKeySelective(cartItem1);
        // 插入新item
        cartItem.setId(null);
        return add(cartItem);
    }


    @Override
    public void clear(Long memberId) {
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andDeleteStatusEqualTo(0);
        OmsCartItem cartItem = new OmsCartItem();
        cartItem.setDeleteStatus(1);
        cartItem.setModifyTime(new Date());
        cartItemMapper.updateByExampleSelective(cartItem, example);
    }

}
