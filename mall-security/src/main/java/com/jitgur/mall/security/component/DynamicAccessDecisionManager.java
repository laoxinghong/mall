package com.jitgur.mall.security.component;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * 动态权限校验管理器，用于判断用户是否有访问权限
 * Created by jitgur on 20230204
 */
public class DynamicAccessDecisionManager implements AccessDecisionManager {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }


    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // 当接口未被配置资源时直接放行
        if (CollUtil.isEmpty(configAttributes)) {
            return;
        }

        // 将访问所需资源与用户拥有的资源进行比对
        for (ConfigAttribute configAttribute : configAttributes) {
            String needAttribute = configAttribute.getAttribute();
            boolean isEquals = false;
            Iterator<? extends GrantedAuthority> iterator = authentication.getAuthorities().iterator();
            while (!isEquals && iterator.hasNext()) {
                GrantedAuthority grantedAuthority = iterator.next();
                if (needAttribute.trim().equals(grantedAuthority.getAuthority())) {
                    isEquals = true;
                }
            }

            if (!isEquals) {
                throw new AccessDeniedException("您没有访问权限！");
            }
        }
    }


}
