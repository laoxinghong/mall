package com.jitgur.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.jitgur.mall.mbg.mapper.UmsMemberReceiveAddressMapper;
import com.jitgur.mall.mbg.model.UmsMember;
import com.jitgur.mall.mbg.model.UmsMemberReceiveAddress;
import com.jitgur.mall.mbg.model.UmsMemberReceiveAddressExample;
import com.jitgur.mall.portal.service.UmsMemberReceiveAddressService;
import com.jitgur.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员收货地址管理service实现类
 * Created by jitgur on 20230212
 */
@Service
public class UmsMemberReceiveAddressServiceImpl implements UmsMemberReceiveAddressService {

    @Autowired
    private UmsMemberReceiveAddressMapper memberReceiveAddressMapper;
    @Autowired
    private UmsMemberService memberService;


    @Override
    public int create(UmsMemberReceiveAddress memberReceiveAddress) {
        UmsMember currentMember = memberService.getCurrentMember();
        memberReceiveAddress.setMemberId(currentMember.getId());
        return memberReceiveAddressMapper.insert(memberReceiveAddress);
    }


    @Override
    public int delete(Long addressId) {
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(addressId);
        return memberReceiveAddressMapper.deleteByExample(example);
    }


    @Override
    public UmsMemberReceiveAddress getItem(Long addressId) {
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(addressId);
        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(addressList)) {
            return addressList.get(0);
        }
        return null;
    }


    @Override
    public List<UmsMemberReceiveAddress> list() {
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId());
        return memberReceiveAddressMapper.selectByExample(example);
    }


    @Override
    public int update(Long addressId, UmsMemberReceiveAddress memberReceiveAddress) {
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(addressId);

        if (memberReceiveAddress.getDefaultStatus().equals(1)) {
            UmsMemberReceiveAddress address = new UmsMemberReceiveAddress();
            UmsMemberReceiveAddressExample example1 = new UmsMemberReceiveAddressExample();
            example1.createCriteria().andMemberIdEqualTo(currentMember.getId()).andDefaultStatusEqualTo(1);
            List<UmsMemberReceiveAddress> addressList = memberReceiveAddressMapper.selectByExample(example1);
            if (CollUtil.isNotEmpty(addressList)) {
                address = addressList.get(0);
            }
            address.setDefaultStatus(0);
            memberReceiveAddressMapper.updateByPrimaryKeySelective(address);
        }

        return memberReceiveAddressMapper.updateByExampleSelective(memberReceiveAddress, example);
    }

}
