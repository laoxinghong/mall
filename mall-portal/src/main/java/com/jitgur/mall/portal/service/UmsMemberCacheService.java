package com.jitgur.mall.portal.service;

import com.jitgur.mall.mbg.model.UmsMember;

/**
 * 会员信息缓存管理service
 * Created by jitgur on 20230211
 */
public interface UmsMemberCacheService {

    /**
     * 设置会员信息缓存
     */
    void setMember(UmsMember member);


    /**
     * 删除会员信息缓存
     */
    void delMember(Long memberId);


    /**
     * 获取会员信息缓存
     */
    UmsMember getMember(String username);


    /**
     * 设置认证码
     */
    void setAuthCode(String telephone,String authCode);


    /**
     * 获取验证码
     */
    String getAuthCode(String telephone);

}
