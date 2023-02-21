package com.jitgur.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.util.StringUtil;
import com.jitgur.mall.common.exception.Asserts;
import com.jitgur.mall.mbg.mapper.UmsMemberLevelMapper;
import com.jitgur.mall.mbg.mapper.UmsMemberMapper;
import com.jitgur.mall.mbg.model.UmsMember;
import com.jitgur.mall.mbg.model.UmsMemberExample;
import com.jitgur.mall.mbg.model.UmsMemberLevel;
import com.jitgur.mall.mbg.model.UmsMemberLevelExample;
import com.jitgur.mall.portal.domain.UmsMemberDetails;
import com.jitgur.mall.portal.service.UmsMemberCacheService;
import com.jitgur.mall.portal.service.UmsMemberService;
import com.jitgur.mall.security.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 会员管理service实现类
 * Created by jitgur on 20230211
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberServiceImpl.class);
    @Autowired
    private UmsMemberCacheService memberCacheService;
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;


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
            memberCacheService.setMember(member);
        }

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


    public boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtil.isEmpty(authCode)) {
            return false;
        }
        String authCode1 = memberCacheService.getAuthCode(telephone);
        return authCode.equals(authCode1);
    }


    @Override
    public void updateMemberIntegration(Long memberId, Integer integration) {
        UmsMember member = memberMapper.selectByPrimaryKey(memberId);
        if (member == null) {
            Asserts.fail("当前用户不存在");
        }
        member.setIntegration(integration);
        memberMapper.updateByPrimaryKeySelective(member);
        memberCacheService.delMember(memberId);
    }


    @Override
    public String refreshToken(String token) {
        UmsMember currentMember = getCurrentMember();
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if (username == null) {
            Asserts.fail("异常，请重试！（请检查token的正确性）");
        }
        if (!username.equals(currentMember.getUsername())) {
            Asserts.fail("不能刷新其他用户的token");
        }
        return jwtTokenUtil.refreshHeadToken(token);
    }


    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("请输入正确的密码");
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }


    @Override
    public void registry(String username, String password, String telephone, String authCode) {

        if (!verifyAuthCode( authCode,telephone)) {
            Asserts.fail("验证码错误");
        }

        // 检查用户是否已注册
        UmsMemberExample memberExample = new UmsMemberExample();
        memberExample.createCriteria().andPhoneEqualTo(telephone).andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(memberList)) {
            Asserts.fail("用户已注册");
        }

        // 注册
        UmsMember member = new UmsMember();
        member.setPassword(passwordEncoder.encode(password));
        member.setUsername(username);
        member.setPhone(telephone);
        member.setStatus(1);
        member.setCreateTime(new Date());

        // 设置会员等级
        UmsMemberLevelExample memberLevelExample = new UmsMemberLevelExample();
        memberLevelExample.createCriteria().andDefaultStatusEqualTo(1);
        List<UmsMemberLevel> levelList = memberLevelMapper.selectByExample(memberLevelExample);
        if (CollUtil.isNotEmpty(levelList)) {
            member.setMemberLevelId(levelList.get(0).getId());
        }

        memberMapper.insert(member);
        member.setPassword(null);
    }

}
