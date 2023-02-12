package com.jitgur.mall.portal.service;

import com.jitgur.mall.mbg.model.UmsMember;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员管理service
 * Created by jitgur on 20230211
 */
public interface UmsMemberService {

    /**
     * 用户注册
     */
    @Transactional
    void registry(String username, String password, String telephone, String authCode);


    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);


    /**
     * 根据会员名获取会员
     */
    UmsMember getMemberByUsername(String username);


    /**
     * 根据id获取会员
     */
    UmsMember getMemberById(Long memberId);


    /**
     * 获取会员详情
     */
    UserDetails loadUserByUsername(String username);


    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();


    /**
     * 修改密码
     */
    @Transactional
    void updatePassword(String telephone, String authCode, String password);


    /**
     * 更新会员积分
     */
    void updateMemberIntegration(Long memberId, Integer integration);


    /**
     * 刷新token
     */
    String refreshToken(String token);


    /**
     * 登录并返回token
     */
    String login(String username, String password);


}
