package com.jitgur.mall.portal.service.impl;

import com.jitgur.mall.mbg.model.UmsMember;
import com.jitgur.mall.portal.domain.UmsMemberProductCollection;
import com.jitgur.mall.portal.repository.UmsMemberProductCollectionRepository;
import com.jitgur.mall.portal.service.UmsMemberProductCollectionService;
import com.jitgur.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 会员商品收藏管理service实现类
 * Created by jitgut on 20230214
 */
@Service
public class UmsMemberProductCollectionServiceImpl implements UmsMemberProductCollectionService {

    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsMemberProductCollectionRepository memberProductCollectionRepository;


    @Override
    public int create(UmsMemberProductCollection memberProductCollection) {
        // 获取当前用户
        UmsMember currentMember = memberService.getCurrentMember();
        // 是否已收藏
        UmsMemberProductCollection collection = memberProductCollectionRepository
                .findByMemberIdAndProductId(currentMember.getId(), memberProductCollection.getProductId());
        if (collection != null) {
            return 0;
        }
        // 填充用户信息
        memberProductCollection.setMemberId(currentMember.getId());
        memberProductCollection.setMemberIcon(currentMember.getIcon());
        memberProductCollection.setMemberNickname(currentMember.getNickname());
        memberProductCollection.setCreateTime(new Date());
        memberProductCollectionRepository.save(memberProductCollection);
        return 1;
    }


    @Override
    public int  delete(Long productId) {
        UmsMember currentMember = memberService.getCurrentMember();
        return memberProductCollectionRepository.deleteByMemberIdAndProductId(currentMember.getId(), productId);
    }


    @Override
    public Page<UmsMemberProductCollection> listPage(Integer pageNum, Integer pageSize) {
        UmsMember currentMember = memberService.getCurrentMember();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return memberProductCollectionRepository.findByMemberId(currentMember.getId(), pageable);
    }


    @Override
    public void clear() {
        UmsMember currentMember = memberService.getCurrentMember();
        memberProductCollectionRepository.deleteAllByMemberId(currentMember.getId());
    }


    @Override
    public List<UmsMemberProductCollection> list(String productName) {
        UmsMember currentMember = memberService.getCurrentMember();
        List<UmsMemberProductCollection> allCollection = memberProductCollectionRepository.findAllByMemberId(currentMember.getId());
        return allCollection.stream().filter(item -> item.getProductName().contains(productName)).collect(Collectors.toList());
    }

}
