package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.UmsMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * 会员详情
 * Created by jitgur on 20230211
 */
public class UmsMemberDetails implements UserDetails {

    private final UmsMember member;


    public UmsMemberDetails(UmsMember member) {
        this.member = member;
    }


    public UmsMember getMember() {
        return member;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户权限
        return Arrays.asList(new SimpleGrantedAuthority("TEST"));
    }


    @Override
    public String getPassword() {
        return member.getPassword();
    }


    @Override
    public String getUsername() {
        return member.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return member.getStatus().equals(1);
    }

}
