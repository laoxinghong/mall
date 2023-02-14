package com.jitgur.mall.portal.service.impl;

import com.jitgur.mall.mbg.model.UmsMember;
import com.jitgur.mall.portal.domain.UmsMemberProductReadHistory;
import com.jitgur.mall.portal.repository.UmsMemberProductReadHistoryRepository;
import com.jitgur.mall.portal.service.UmsMemberProductReadHistoryService;
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
 * 会员商品浏览记录管理service实现类
 * Created by jitgur on 20230214
 */
@Service
public class UmsMemberProductReadHistoryServiceImpl implements UmsMemberProductReadHistoryService {

    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsMemberProductReadHistoryRepository memberProductReadHistoryRepository;


    @Override
    public void create(UmsMemberProductReadHistory readHistory) {
        UmsMember currentMember = memberService.getCurrentMember();
        readHistory.setMemberId(currentMember.getId());
        readHistory.setMemberIcon(currentMember.getIcon());
        readHistory.setMemberNickname(currentMember.getNickname());
        readHistory.setCreateTime(new Date());
        memberProductReadHistoryRepository.save(readHistory);
    }


    @Override
    public void delete(String id) {
        memberProductReadHistoryRepository.deleteById(id);
    }


    @Override
    public int deleteAll(List<String> idList) {
        memberProductReadHistoryRepository.deleteAllById(idList);
        return idList.size();
    }


    @Override
    public List<UmsMemberProductReadHistory> list(String productName) {
        UmsMember currentMember = memberService.getCurrentMember();
        List<UmsMemberProductReadHistory> allReadHistory = memberProductReadHistoryRepository.findAllByMemberId(currentMember.getId());
        return allReadHistory.stream().filter(item -> item.getProductName().contains(productName)).collect(Collectors.toList());
    }


    @Override
    public Page<UmsMemberProductReadHistory> listPage(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        UmsMember currentMember = memberService.getCurrentMember();
        return memberProductReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(currentMember.getId(), pageable);
    }


    @Override
    public void clear() {
        UmsMember currentMember = memberService.getCurrentMember();
        memberProductReadHistoryRepository.deleteAllByMemberId(currentMember.getId());
    }

}
