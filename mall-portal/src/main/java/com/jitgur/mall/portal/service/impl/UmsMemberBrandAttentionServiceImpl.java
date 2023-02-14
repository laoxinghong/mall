package com.jitgur.mall.portal.service.impl;

import com.jitgur.mall.mbg.model.UmsMember;
import com.jitgur.mall.portal.domain.UmsMemberBrandAttention;
import com.jitgur.mall.portal.repository.UmsMemberBrandAttentionRepository;
import com.jitgur.mall.portal.service.UmsMemberBrandAttentionService;
import com.jitgur.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 会员品牌关注管理service
 * Created by jitgur on 20230214
 */
@Service
public class UmsMemberBrandAttentionServiceImpl implements UmsMemberBrandAttentionService {

    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsMemberBrandAttentionRepository memberBrandAttentionRepository;


    @Override
    public int add(UmsMemberBrandAttention memberBrandAttention) {
        // 获取当前用户
        UmsMember currentMember = memberService.getCurrentMember();
        // 是否已关注该品牌
        UmsMemberBrandAttention attention = memberBrandAttentionRepository.findByMemberIdAndBrandId(currentMember.getId(), memberBrandAttention.getBrandId());
        if (attention != null) {
            return 0;
        }
        // 填充用户信息
        memberBrandAttention.setMemberId(currentMember.getId());
        memberBrandAttention.setMemberIcon(currentMember.getIcon());
        memberBrandAttention.setMemberNickname(currentMember.getNickname());
        memberBrandAttention.setCreateTime(new Date());
        // 添加关注
        memberBrandAttentionRepository.save(memberBrandAttention);
        return 1;
    }


    @Override
    public int delete(Long brandId) {
        UmsMember currentMember = memberService.getCurrentMember();
        return memberBrandAttentionRepository.deleteByBrandIdAndMemberId(brandId, currentMember.getId());
    }


    @Override
    public List<UmsMemberBrandAttention> detail(String brandName) {
        UmsMember currentMember = memberService.getCurrentMember();
        return memberBrandAttentionRepository.findByMemberIdAndBrandName(currentMember.getId(), brandName);
    }


    @Override
    public Page<UmsMemberBrandAttention> listPage(Integer pageNum, Integer pageSize) {
        UmsMember currentMember = memberService.getCurrentMember();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return memberBrandAttentionRepository.findByMemberId(currentMember.getId(), pageable);
    }


    @Override
    public void clear() {
        UmsMember currentMember = memberService.getCurrentMember();
        memberBrandAttentionRepository.deleteAllByMemberId(currentMember.getId());
    }

}
