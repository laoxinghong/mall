package com.jitgur.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.util.StringUtil;
import com.jitgur.mall.common.exception.Asserts;
import com.jitgur.mall.mbg.mapper.UmsMemberMapper;
import com.jitgur.mall.mbg.model.UmsMember;
import com.jitgur.mall.mbg.model.UmsMemberExample;
import com.jitgur.mall.portal.domain.UmsMemberDetails;
import com.jitgur.mall.portal.service.UmsMemberCacheService;
import com.jitgur.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * 会员管理service实现类
 * Created by jitgur on 20230211
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private UmsMemberCacheService memberCacheService;
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }

        // 保存到redis
        memberCacheService.setAuthCode(telephone, code.toString());
        return code.toString();
    }


    @Override
    public UmsMember getMemberByUsername(String username) {
        // 到缓存中获取
        UmsMember member = memberCacheService.getMember(username);
        if (member != null) {
            return member;
        }
        // 缓存中没有，再到数据库获取
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(memberList)) {
            member = memberList.get(0);
        }
        memberCacheService.setMember(member);
        return member;
    }


    @Override
    public UmsMember getMemberById(Long memberId) {
        return memberMapper.selectByPrimaryKey(memberId);
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember member = getMemberByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException("当前用户不存在");
        }
        return new UmsMemberDetails(member);
    }


    @Override
    public UmsMember getCurrentMember() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UmsMemberDetails memberDetails = (UmsMemberDetails) authentication.getPrincipal();
        return memberDetails.getMember();
    }


    @Override
    public void updatePassword(String telephone, String authCode, String password) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andPhoneEqualTo(telephone);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (CollUtil.isEmpty(memberList)) {
            Asserts.fail("当前用户不存在");
        }

        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }

        UmsMember member = memberList.get(0);
        member.setPassword(passwordEncoder.encode(password));
        memberCacheService.delMember(member.getId());
        memberMapper.updateByPrimaryKeySelective(member);
    }


    public Boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtil.isEmpty(authCode)) {
            return false;
        }
        String authCode1 = memberCacheService.getAuthCode(telephone);
        return authCode.equals(authCode1);
    }


    @Override
    public void updateMemberIntegration(Long memberId, Integer integration) {

    }


    @Override
    public String refreshToken(String token) {
        return null;
    }


    @Override
    public String login(String username, String password) {
        return null;
    }


    @Override
    public UmsMember registry(String username, String password, String telephone, String authCode) {
        return null;
    }

}
